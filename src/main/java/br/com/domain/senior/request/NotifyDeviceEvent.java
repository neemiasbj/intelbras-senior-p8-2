package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.enums.DeviceCommType;

public class NotifyDeviceEvent extends HeaderMessage {
    private final int deviceID;
    
    private final Date eventDate;
    
    private final short gmt;
    
    private final DeviceCommType generatedOnline;
    
    private final byte eventType;
    
    public NotifyDeviceEvent(byte messageNumber, int deviceID, Date eventDate, short gmt, DeviceCommType generatedOnline, byte eventType) {
        super(messageNumber, (byte) 3, (byte) 4);
        this.deviceID = deviceID;
        this.eventDate = new Date(eventDate == null ? 0L : eventDate.getTime());
        this.gmt = gmt;
        this.generatedOnline = generatedOnline;
        this.eventType = eventType;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(21);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt((int) (this.eventDate.getTime() / 1000L));
        buf.putShort(this.gmt);
        buf.put(this.generatedOnline.getId());
        buf.put(this.eventType);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
    @Override
    public String toString() {
        return "NotifyDeviceEvent [deviceID=" + deviceID + ", eventDate=" + eventDate + ", gmt=" + gmt + ", generatedOnline=" + generatedOnline
               + ", eventType=" + eventType + "]";
    }
    
}
