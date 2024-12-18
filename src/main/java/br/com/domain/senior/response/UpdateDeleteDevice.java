package br.com.domain.senior.response;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.senior.abstration.DeviceCollectionMessage;

public class UpdateDeleteDevice extends DeviceCollectionMessage {
    private byte operation;
    
    private final List<Integer> devicesIds = new ArrayList<>();
    
    public UpdateDeleteDevice(byte msgNumber, byte messageType, byte messageId) {
        super(msgNumber, messageType, messageId);
    }
    
    public byte getOperation() {
        return this.operation;
    }
    
    public void setOperation(byte operation) {
        this.operation = operation;
    }
    
    public List<Integer> getDeletedDevicesIds() {
        return this.devicesIds;
    }
    
    public void addDeletedDeviceId(int deviceId) {
        this.devicesIds.add(deviceId);
    }
    
}
