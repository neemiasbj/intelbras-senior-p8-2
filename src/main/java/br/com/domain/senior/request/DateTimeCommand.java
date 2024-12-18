package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class DateTimeCommand extends HeaderMessage {
    private final int deviceId;
    
    public DateTimeCommand(byte messageNumber, int deviceID) {
        super(messageNumber, (byte) 4, (byte) 1);
        this.deviceId = deviceID;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
