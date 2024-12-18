package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.enums.DeviceCommType;

public class NotifyDeviceTieDefect extends HeaderMessage {
    private final int deviceID;
    
    private final Date eventDate;
    
    private final short gmt;
    
    private final DeviceCommType generatedOnline;
    
    private final byte tie;
    
    private final boolean active;
    
    public NotifyDeviceTieDefect(byte messageNumber, int deviceID, Date eventDate, short gmt, DeviceCommType generatedOnline, byte tie,
                                 boolean active) {
        super(messageNumber, (byte) 3, (byte) 2);
        this.deviceID = deviceID;
        this.eventDate = new Date(eventDate == null ? 0L : eventDate.getTime());
        this.gmt = gmt;
        this.generatedOnline = generatedOnline;
        this.tie = tie;
        this.active = active;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(22);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt((int) (this.eventDate.getTime() / 1000L));
        buf.putShort(this.gmt);
        buf.put(this.generatedOnline.getId());
        buf.put(this.tie);
        buf.put((byte) (this.active ? 1 : 0));
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
