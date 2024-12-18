package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestAccessValidation extends HeaderMessage {
    private final int deviceID;
    
    private final Date eventDate;
    
    private final short gmtOffset;
    
    private final long cardID;
    
    private final boolean holiday;
    
    private final String personId;
    
    private final String licensePlate;
    
    public RequestAccessValidation(byte messageNumber, int deviceID, Date eventDate, short gmtOffset, boolean holiday, long cardID, String personId,
                                   String licensePlate) {
        super(messageNumber, (byte) 2, (byte) 2);
        this.deviceID = deviceID;
        this.eventDate = new Date(eventDate == null ? 0L : eventDate.getTime());
        this.gmtOffset = gmtOffset;
        this.holiday = holiday;
        this.cardID = cardID;
        this.personId = personId;
        this.licensePlate = licensePlate;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(61);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt((int) (this.eventDate.getTime() / 1000L));
        buf.putShort(this.gmtOffset);
        buf.put((byte) (this.holiday ? 1 : 0));
        buf.putLong(this.cardID);
        // int idPersonSize = true;
        byte[] personArray = new byte[23];
        if (this.personId != null) {
            int personIdLength = this.personId.length() > 23 ? 23 : this.personId.length();
            System.arraycopy(this.personId.getBytes(), 0, personArray, 23 - personIdLength, personIdLength);
        }
        
        buf.put(personArray);
        // int licensePlateSize = true;
        byte[] plateArray = new byte[10];
        if (this.personId != null) {
            int licensePlateLength = this.licensePlate.length() > 10 ? 10 : this.licensePlate.length();
            System.arraycopy(this.licensePlate.getBytes(), 0, plateArray, 10 - licensePlateLength, licensePlateLength);
        }
        
        buf.put(plateArray);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
