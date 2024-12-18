package br.com.senior.service.processor;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.domain.intelbras.model.api.DateTimeModel;
import br.com.domain.intelbras.model.api.ResponseDeviceGenericModel;
import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.response.DateTimeResponse;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;
import br.com.util.Utils;

public final class DateTimeResponseProcessor extends ResponseProcessor<DateTimeResponse> {
    public DateTimeResponseProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    @Override
    public void process(DateTimeResponse message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            Collection<ManagerDevice> var2 = CONTROLLER.getAllDevices();
            
            List<ManagerDevice> device = var2.stream().filter(ManagerDevice::isDateTimeStatus).toList();
            device.forEach(t -> {
                ResponseEntity<ResponseDeviceGenericModel> responseEntity;
                if (t.isDateTimeStatus()) {
                    t.setDateTimeStatus(false);
                    t.setDate(message.getDate());
                    DateTimeModel dateTimeSendDevice = new DateTimeModel(t.getIp());
                    dateTimeSendDevice.setDateTime(Utils.dfWebhookFormat.format(message.getDate()));
                    responseEntity = intelbrasApiService.setDateTime(dateTimeSendDevice);
                    
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        logger.info("Device {} response success command DateHour: {}", t.getIp(), responseEntity.getBody());
                    } else {
                        logger.info("Device {} response command DateHour: {}", t.getIp(), responseEntity.getBody());
                    }
                }
            });
        }
    }
    
}
