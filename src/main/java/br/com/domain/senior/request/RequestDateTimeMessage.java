package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestDateTimeMessage extends HeaderMessage {
    public RequestDateTimeMessage(byte msgNumber) {
        super(msgNumber, (byte) 4, (byte) 4);
    }
    
    public ByteBuffer encode() {
        int headerSize = super.getHeader().remaining();
        
        ByteBuffer buf = ByteBuffer.allocate(headerSize);
        buf.put(super.getHeader());
        buf.putInt(0, headerSize);
        buf.flip();
        
        return buf;
    }
    
}
