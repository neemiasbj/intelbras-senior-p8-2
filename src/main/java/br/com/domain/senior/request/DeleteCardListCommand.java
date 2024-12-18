package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class DeleteCardListCommand extends HeaderMessage {
    private final int devId;
    
    public DeleteCardListCommand(byte msgNumber, int devId) {
        super(msgNumber, (byte) 5, (byte) 6);
        this.devId = devId;
    }
    
    public int getDeviceId() {
        return this.devId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
