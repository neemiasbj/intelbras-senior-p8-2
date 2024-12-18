package br.com.senior.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.senior.service.queue.MessageQueue;
import br.com.senior.service.socket.SocketCommunicator;
import br.com.senior.usecase.AutenticationMiddlewareUseCase;

@Service
public class ClientCSMCommunication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger("ClientCSMCommunication");

	@Autowired
	private AutenticationMiddlewareUseCase autenticationMiddlewareUseCase;
	private final MessageQueue queue;
	private final SocketCommunicator socketCommunicator;
	private final IntelbrasService intelbrasService;
	private final Queue<HeaderMessage> messageQueue = new LinkedBlockingQueue<>();
	private final Map<Byte, CompletableFuture<HeaderMessage>> responseFutures = new ConcurrentHashMap<>();

	private volatile boolean running;

	public ClientCSMCommunication(SocketCommunicator socketCommunicator, IntelbrasService intelbrasService,
			MessageQueue queue) {
		this.queue = queue;
		this.socketCommunicator = socketCommunicator;
		this.intelbrasService = intelbrasService;
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			initializeCommunication();
		} catch (IOException | InterruptedException e) {
			stopRunning();
			logger.error("Error initializing communication: {}", e.getMessage());
		}
	}

	private void initializeCommunication() throws IOException, InterruptedException {
		establishConnection();
		isConnectedSocket();
		startListenerThread();
		startProcessorThread();
		sendAuthentication();
	}

	private void establishConnection() throws IOException, InterruptedException {
		socketCommunicator.connectSocket();
		logger.info("Socket connection successfully established.");
	}

	private void startListenerThread() {
		Thread messageListenerThread = new Thread(new ClientMessageListener(socketCommunicator, this, queue),
				"MsgListenThread");
		messageListenerThread.start();
	}

	private void startProcessorThread() {
		Thread messageProcessorThread = new Thread(
				new ClientMessageProcessor(socketCommunicator, intelbrasService, this, queue), "MsgProcessThread");
		messageProcessorThread.start();
	}

	public void stop() {
		stopRunning();
		disconnectSocket();
	}

	private void disconnectSocket() {
		try {
			socketCommunicator.disconnect();
			logger.info("Socket connection closed.");
		} catch (IOException e) {
			logger.error("Error closing socket connection: {}", e.getMessage());
		}
	}

	public void sendMessage(HeaderMessage message) throws IOException, InterruptedException {
		if (socketCommunicator.isConnected()) {
			socketCommunicator.write(message.encode(), 1500L);
		} else {
			enqueueMessage(message);
			logger.warn("Socket disconnected. Message queued: {}", message);
		}
	}

	private void enqueueMessage(HeaderMessage message) {
		messageQueue.add(message);
	}

	@Scheduled(fixedDelay = 10000)
	private void checkAndResendMessages() {
		if (socketCommunicator.isConnected()) {
			resendQueuedMessages();
		} else {
			logger.warn("Socket disconnected. Queued messages awaiting reconnection: {}", messageQueue.size());
			tryReconnect();
		}
	}

	private void resendQueuedMessages() {
		while (!messageQueue.isEmpty() && socketCommunicator.isConnected()) {
			HeaderMessage message = messageQueue.poll();
			try {
				sendMessage(message);
				logger.info("Re-sent queued message: {}", message);
			} catch (IOException | InterruptedException e) {
				enqueueMessage(message);
				logger.warn("Re-send failed, message re-queued: {}", message);
				break;
			}
		}
	}

	private void tryReconnect() {
		new Thread(() -> {
			while (!socketCommunicator.isConnected() && running) {
				try {
					Thread.sleep(5000);
					establishConnection();
				} catch (IOException | InterruptedException e) {
					logger.warn("Reconnection attempt failed. Will retry...");
				}
			}
		}, "ReconnectThread").start();
	}

	public void sendAuthentication() throws IOException, InterruptedException {
		HeaderMessage authMessage = autenticationMiddlewareUseCase.sendAuthentication();
		sendMessage(authMessage);
	}

	public CompletableFuture<HeaderMessage> sendMessageWithResponse(HeaderMessage message)
			throws IOException, InterruptedException {
		CompletableFuture<HeaderMessage> futureResponse = new CompletableFuture<>();
		responseFutures.put(message.getMessageNumber(), futureResponse);
		socketCommunicator.write(ByteBuffer.wrap(message.encode().array()), 1500L);
		return futureResponse;
	}

	public CompletableFuture<HeaderMessage> receiveMessageWithResponse(HeaderMessage receivedMessage) {
		CompletableFuture<HeaderMessage> futureResponse = responseFutures.remove(receivedMessage.getMessageNumber());
		if (futureResponse != null) {
			futureResponse.complete(receivedMessage);
		}
		return futureResponse;
	}

	public boolean hasPendingResponse(HeaderMessage receivedMessage) {
		return responseFutures.containsKey(receivedMessage.getMessageNumber());
	}

	public void completeResponse(HeaderMessage response) {
		CompletableFuture<HeaderMessage> future = responseFutures.remove(response.getMessageNumber());
		if (future != null) {
			future.complete(response);
		}
	}

	private void stopRunning() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	private void isConnectedSocket() {
		running = socketCommunicator.isConnected();
	}

}
