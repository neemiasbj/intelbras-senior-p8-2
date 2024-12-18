package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class CollectEventsCommand extends HeaderMessage {
    private final int deviceId;
    
    private final int startingDate;
    
    private final int finalDate;
    
    private final long startingNSR;
    
    private final long finalNSR;
    
    public CollectEventsCommand(byte messageNumber, int devID, int startingDate, int finalDate, long startingNSR, long finalNSR) {
        super(messageNumber, (byte) 6, (byte) 9);
        this.deviceId = devID;
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.startingNSR = startingNSR;
        this.finalNSR = finalNSR;
    }
    
    public long getStartingNSR() {
        return this.startingNSR;
    }
    
    public long getFinalNSR() {
        return this.finalNSR;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public int getStartingDate() {
        return this.startingDate;
    }
    
    public int getFinalDate() {
        return this.finalDate;
    }
    
}
