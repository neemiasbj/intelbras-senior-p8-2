package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class UnlockDeviceCommand extends HeaderMessage {
    private final int deviceId;
    
    public UnlockDeviceCommand(byte messageNumber, int devID) {
        super(messageNumber, (byte) 5, (byte) 7);
        this.deviceId = devID;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
