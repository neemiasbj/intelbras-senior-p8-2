package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.enums.DeviceCommType;

public class NotifyDeviceAlarmed extends HeaderMessage {
    private final int deviceID;
    
    private final Date eventDate;
    
    private final DeviceCommType generatedOnline;
    
    private final boolean active;
    
    private final short gmt;
    
    public NotifyDeviceAlarmed(byte messageNumber, int deviceID, Date eventDate, short gmt, DeviceCommType generatedOnline, boolean active) {
        super(messageNumber, (byte) 3, (byte) 1);
        this.deviceID = deviceID;
        this.eventDate = new Date(eventDate == null ? 0L : eventDate.getTime());
        this.gmt = gmt;
        this.generatedOnline = generatedOnline;
        this.active = active;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(21);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt((int) (this.eventDate.getTime() / 1000L));
        buf.putShort(this.gmt);
        buf.put(this.generatedOnline.getId());
        buf.put((byte) (this.active ? 1 : 0));
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
