package br.com.senior.client;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.request.IsAliveCommand;
import br.com.domain.senior.response.ACK;
import br.com.domain.senior.response.AuthenticationSuccessfull;
import br.com.domain.senior.response.DeviceDataResponse;
import br.com.domain.senior.response.DriverAlive;
import br.com.domain.senior.response.NACK;
import br.com.senior.service.devices.DevicesController;
import br.com.senior.service.message.HeaderMessageDecodeFactory;
import br.com.senior.service.queue.MessageQueue;
import br.com.senior.service.socket.SocketCommunicator;
import br.com.util.LogUtils;

public class ClientMessageListener implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("ClientMessageListener");

	private final MessageQueue queue;

	private final SocketCommunicator socketCommunicator;

	private final ClientCSMCommunication csmCommunication;

	public ClientMessageListener(SocketCommunicator socketCommunicator, ClientCSMCommunication csmCommunication,
			MessageQueue queue) {
		this.socketCommunicator = socketCommunicator;
		this.csmCommunication = csmCommunication;
		this.queue = queue;
	}

	@Override
	public void run() {
		ByteBuffer buffSize = ByteBuffer.allocate(4);

		while (csmCommunication.isRunning()) {
			ByteBuffer conteudo = null;

			int bytesRead;
			try {

				bytesRead = socketCommunicator.getSocketChannel().read(buffSize);
				if (bytesRead > 0) {
					buffSize.flip();
					int size = buffSize.getInt();
					buffSize.clear();

					conteudo = ByteBuffer.allocate(size - buffSize.capacity());

					while (conteudo.hasRemaining()) {
						bytesRead = socketCommunicator.getSocketChannel().read(conteudo);
						if (bytesRead == -1) {
							throw new IOException("Desconectou");
						}
					}

					conteudo.flip();
					processMessage(conteudo);
				}
			} catch (SocketTimeoutException e) {
				logger.error("Timeout no socket de comunicação: {}", e.getMessage());
			} catch (IOException e) {
				handleIOException(e);
			} catch (InterruptedException e) {
				handleInterruptedException(e);
			}
		}

		logger.info("O driver parou de ler as mensagens. Não está mais conectado ao SDK.");
	}

	private void processMessage(ByteBuffer buffer) throws IOException, InterruptedException {
		HeaderMessage decodedMessage = HeaderMessageDecodeFactory.decode(buffer);
		LogUtils.logInfoInterpreterMessage(decodedMessage, logger);

		if (csmCommunication.hasPendingResponse(decodedMessage)) {
			logger.info("|--> Mensagem aguardando resposta assíncrona recebida: {}", decodedMessage);
			csmCommunication.receiveMessageWithResponse(decodedMessage);
		} else {
			routeMessage(decodedMessage);
		}
	}

	private void routeMessage(HeaderMessage decodedMessage) throws IOException, InterruptedException {
		if (decodedMessage instanceof IsAliveCommand isAliveCommand) {
			respondToAliveCommand(isAliveCommand);
		} else if (decodedMessage instanceof AuthenticationSuccessfull) {
			requestDeviceData();
		} else if (decodedMessage instanceof DeviceDataResponse) {
			logger.info("|--> Recebido DeviceDataResponse: {}", decodedMessage);
		} else if (decodedMessage instanceof NACK || decodedMessage instanceof ACK) {
			logger.info("|--> Mensagem de confirmação recebida: {}", decodedMessage);
		} else {
			logger.info("|--> Enfileirando mensagem para processamento: {}", decodedMessage);
			queue.addItem(decodedMessage);
		}
	}

	private void respondToAliveCommand(IsAliveCommand isAliveCommand) throws IOException, InterruptedException {
		HeaderMessage responseMessage = new DriverAlive(isAliveCommand.getMessageNumber());
		csmCommunication.sendMessage(responseMessage);
	}

	private void requestDeviceData() throws IOException, InterruptedException {
		HeaderMessage requestMessage = DevicesController.sendRequestDevice();
		csmCommunication.sendMessage(requestMessage);
	}

	private void handleIOException(IOException e) {
		logger.error("Erro de I/O na escuta de mensagens: {}", e.getMessage(), e);
		csmCommunication.stop();
		try {
			socketCommunicator.disconnect();
		} catch (IOException ex) {
			logger.error("Erro ao encerrar a intelbrasão: {}", ex.getMessage(), ex);
		}
	}

	private void handleInterruptedException(InterruptedException e) {
		logger.error("Thread foi interrompida: {}", e.getMessage(), e);
		Thread.currentThread().interrupt();
	}

}
