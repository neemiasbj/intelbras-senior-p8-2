package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class CollectEventsResponse extends HeaderMessage {
    private final int deviceID;
    
    private final int acessEventsCount;
    
    private final int alarmeEventsCount;
    
    public CollectEventsResponse(byte messageNumber, int deviceID, int acessEventsCount, int alarmeEventsCount) {
        super(messageNumber, (byte) 6, (byte) 9);
        this.deviceID = deviceID;
        this.acessEventsCount = acessEventsCount;
        this.alarmeEventsCount = alarmeEventsCount;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt(this.acessEventsCount);
        buf.putInt(this.alarmeEventsCount);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
