package br.com.senior.client;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.senior.service.command.InteractionCommands;
import br.com.senior.service.message.HeaderMessageFactoryImpl;
import br.com.senior.service.message.ResponseProcessorFactory;
import br.com.senior.service.queue.MessageQueue;
import br.com.senior.service.socket.SocketCommunicator;

public class ClientMessageProcessor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("ClientMessageProcessor");

	private volatile boolean running = true;

	private final MessageQueue messageQueue;

	private final ClientCSMCommunication csmCommunication;

	private final ResponseProcessorFactory responseProcessorFactory;

	private final InteractionCommands interactionCommands;

	public ClientMessageProcessor(SocketCommunicator socketCommunicator, IntelbrasService intelbrasApiService,
			ClientCSMCommunication csmCommunication, MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
		this.csmCommunication = csmCommunication;
		this.interactionCommands = new InteractionCommands(csmCommunication);
		this.responseProcessorFactory = new ResponseProcessorFactory(csmCommunication, intelbrasApiService,
				interactionCommands);
	}

	@Override
	public void run() {
		while (running) {
			try {
				processMessages();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				logger.error("Thread interrompida: {}", e.getMessage(), e);
				Thread.currentThread().interrupt();
				stop();
			} catch (Exception e) {
				logger.error("Erro ao processar mensagens: {}", e.getMessage(), e);
			}
		}
	}

	private void processMessages() {
		List<HeaderMessage> allMessages = messageQueue.getAllItems();
		if (allMessages.isEmpty())
			return;

		for (HeaderMessage message : allMessages) {
			try {
				processSingleMessage(message);
				messageQueue.pollItem(message.getMessageNumber());
			} catch (Exception e) {
				logger.error("Erro ao processar mensagem individual: {}", e.getMessage(), e);
			}
		}
	}

	private void processSingleMessage(HeaderMessage message) throws Exception {
		if (message == null) {
			logger.warn("Mensagem nula encontrada na fila.");
			return;
		}

		ResponseProcessor<HeaderMessage> processor = responseProcessorFactory.getResponse(message);
		if (processor != null) {
			sendReceiveAcknowledgment(message);
			processor.process(message);
		} else {
			logger.warn("Nenhum ResponseProcessor registrado para a mensagem: {}", message);
		}
	}

	private void sendReceiveAcknowledgment(HeaderMessage message) throws IOException, InterruptedException {
		HeaderMessage recv = HeaderMessageFactoryImpl.getInstance().buildRECV(message.getMessageNumber());
		csmCommunication.sendMessage(recv);
	}

	public void stop() {
		running = false;
	}

}
