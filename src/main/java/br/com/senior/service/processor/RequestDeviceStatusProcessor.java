package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.RequestDeviceStatusCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;
import br.com.util.DateFormatUtil;

public final class RequestDeviceStatusProcessor extends ResponseProcessor<RequestDeviceStatusCommand> {
    public RequestDeviceStatusProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(RequestDeviceStatusCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            String[] properties = new String[] { "Comunicação=" + device.getStatus().getDescription(),
                                                 "Memória disponível=532 Mb",
                                                 "Quantidade de entradas=" + device.getInputDevices().size(),
                                                 "Quantidade de saídas=" + device.getOutputDevices().size(),
                                                 "Data/Hora=" + DateFormatUtil.formatDateTimeToString(device.getDate().getTime()),
                                                 "Display=Exemplo de Status" };
            HeaderMessage response = MSG_FACTORY.buildDeviceStatusResponse(message.getMessageNumber(), properties);
            this.driver.sendMessage(response);
        }
    }
    
}
