package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricTechnology;

public class UpdateDeleteBiometricTechnology extends HeaderMessage {
    private byte operation;
    
    private BiometricTechnology bioTechnology;
    
    private final List<Integer> devicesIds = new ArrayList<>();
    
    public UpdateDeleteBiometricTechnology(byte msgNumber) {
        super(msgNumber, (byte) 5, (byte) 34);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public byte getOperation() {
        return this.operation;
    }
    
    public void setOperation(byte operation) {
        this.operation = operation;
    }
    
    public void setBioTechnology(BiometricTechnology bioTechnology) {
        this.bioTechnology = bioTechnology;
    }
    
    public BiometricTechnology getBioTechnology() {
        return this.bioTechnology;
    }
    
    public void addDeviceIds(int deviceId) {
        this.devicesIds.add(deviceId);
    }
    
    public List<Integer> getDevicesIds() {
        return this.devicesIds;
    }
    
}
