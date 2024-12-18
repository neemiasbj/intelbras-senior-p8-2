package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestDeviceStatusCommand extends HeaderMessage {
    private final int deviceID;
    
    public RequestDeviceStatusCommand(byte messageNumber, int deviceID) {
        super(messageNumber, (byte) 6, (byte) 1);
        this.deviceID = deviceID;
    }
    
    public int getDeviceId() {
        return this.deviceID;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
