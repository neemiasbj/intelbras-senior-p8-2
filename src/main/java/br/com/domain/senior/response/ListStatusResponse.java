package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;

public class ListStatusResponse extends HeaderMessage {
    private final int deviceID;
    
    private final int usedBytes;
    
    private final int availableBytes;
    
    private final Date lastUpdate;
    
    private final int recordCount;
    
    public ListStatusResponse(byte messageNumber, int deviceID, int recordCount, int usedBytes, int availableBytes, Date lastUpdate) {
        super(messageNumber, (byte) 5, (byte) 0);
        this.deviceID = deviceID;
        this.usedBytes = usedBytes;
        this.availableBytes = availableBytes;
        this.lastUpdate = lastUpdate;
        this.recordCount = recordCount;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt(this.recordCount);
        buf.putInt(this.usedBytes);
        buf.putInt(this.availableBytes);
        buf.putLong(this.lastUpdate.getTime() / 1000L);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
