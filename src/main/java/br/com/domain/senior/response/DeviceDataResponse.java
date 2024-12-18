package br.com.domain.senior.response;

import br.com.domain.senior.abstration.DeviceCollectionMessage;

public class DeviceDataResponse extends DeviceCollectionMessage {
    public DeviceDataResponse(byte msgNumber, byte messageType, byte messageId) {
        super(msgNumber, messageType, messageId);
    }
    
}
