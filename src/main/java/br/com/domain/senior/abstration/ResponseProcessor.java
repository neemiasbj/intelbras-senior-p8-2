package br.com.domain.senior.abstration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.response.NACK;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;
import br.com.senior.service.devices.DevicesController;
import br.com.senior.service.message.HeaderMessageFactory;
import br.com.senior.service.message.HeaderMessageFactoryImpl;

public abstract class ResponseProcessor<M extends HeaderMessage> {
    
    protected static final Logger logger = LoggerFactory.getLogger("ResponseProcessor");
    
    protected static final HeaderMessageFactory MSG_FACTORY = HeaderMessageFactoryImpl.getInstance();
    
	protected static final DevicesController CONTROLLER = new DevicesController();
    
    protected final ClientCSMCommunication driver;
    
    protected final IntelbrasService intelbrasApiService;
    
    protected final InteractionCommands interactionCommands;
    
    protected ResponseProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        if (driver == null) {
            throw new NullPointerException("Driver não pode ser nulo.");
        }
        if (intelbrasApiService == null) {
            throw new NullPointerException("Intelbras API Client não pode ser nulo.");
        }
        this.driver = driver;
        this.intelbrasApiService = intelbrasApiService;
        this.interactionCommands = interactionCommands;
    }
    
    protected NACK messageSendDeviceNack(byte numeroMessage) {
        return new NACK(numeroMessage, "Erro ao enviar comando para o dispositivo.", (byte) 51);
    }
    
    public abstract void process(M var1) throws Exception;
    
}
