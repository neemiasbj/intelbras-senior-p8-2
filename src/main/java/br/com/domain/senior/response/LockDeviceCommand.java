package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class LockDeviceCommand extends HeaderMessage {
    private final int deviceID;
    
    public LockDeviceCommand(byte messageNumber, int devID) {
        super(messageNumber, (byte) 5, (byte) 7);
        this.deviceID = devID;
    }
    
    public int getDeviceId() {
        return this.deviceID;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
