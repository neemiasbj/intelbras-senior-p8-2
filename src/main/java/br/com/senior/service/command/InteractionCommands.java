package br.com.senior.service.command;

import java.io.IOException;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.message.HeaderMessageFactory;
import br.com.senior.service.message.HeaderMessageFactoryImpl;

public class InteractionCommands {

	private final ClientCSMCommunication clientCSMCommunication;

	private static final HeaderMessageFactory MSG_FACTORY = HeaderMessageFactoryImpl.getInstance();

	public InteractionCommands(ClientCSMCommunication clientCSMCommunication) {
		super();
		this.clientCSMCommunication = clientCSMCommunication;
	}

	public void sendRequestDateTime() throws IOException, InterruptedException {
		HeaderMessage request = MSG_FACTORY.buildRequestDateTime();
		this.clientCSMCommunication.sendMessage(request);
	}

}
