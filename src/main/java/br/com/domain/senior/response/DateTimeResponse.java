package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;

public class DateTimeResponse extends HeaderMessage {
    private final Date date;
    
    public DateTimeResponse(byte messageNumber, Date date) {
        super(messageNumber, (byte) 4, (byte) 4);
        this.date = date;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public Date getDate() {
        return this.date;
    }
    
}
