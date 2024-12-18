package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class CollectBiometriesCommand extends HeaderMessage {
    private final int deviceId;
    
    public CollectBiometriesCommand(byte messageNumber, int devID) {
        super(messageNumber, (byte) 6, (byte) 17);
        this.deviceId = devID;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
}
