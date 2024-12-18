package br.com.senior.service.processor;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import br.com.domain.intelbras.model.api.DeviceEmergencyModel;
import br.com.domain.intelbras.model.api.ResponseDeviceGenericModel;
import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.DeactivateEmergencyCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class DeactivateEmergencyProcessor extends ResponseProcessor<DeactivateEmergencyCommand> {
    public DeactivateEmergencyProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(DeactivateEmergencyCommand message) throws IOException, InterruptedException {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            HeaderMessage response;
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            
            ResponseEntity<ResponseDeviceGenericModel> responseEntity = intelbrasApiService.unsetEmergencyStatus(new DeviceEmergencyModel(
                                            device.getIp()));
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ResponseDeviceGenericModel emergency = responseEntity.getBody();
                if (Boolean.TRUE.equals(emergency.getError()))
                    response = MSG_FACTORY.buildACK(message.getMessageNumber());
                else
                    response = MSG_FACTORY.buildNACK(message.getMessageNumber(), "Erro gerado no dispositivo ao processar a mensagem.", (byte) 3);
            } else {
                response = messageSendDeviceNack(message.getMessageNumber());
                
            }
            
            this.driver.sendMessage(response);
        }
    }
    
}
