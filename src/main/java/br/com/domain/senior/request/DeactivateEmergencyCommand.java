package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class DeactivateEmergencyCommand extends HeaderMessage {
    private final int deviceId;
    
    public DeactivateEmergencyCommand(byte messageNumber, int devId) {
        super(messageNumber, (byte) 5, (byte) 7);
        this.deviceId = devId;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
