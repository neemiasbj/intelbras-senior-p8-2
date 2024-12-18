package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class ACK extends HeaderMessage {
    public ACK(byte msgNumber) {
        super(msgNumber, (byte) 0, (byte) 0);
    }
    
    public ByteBuffer encode() {
        ByteBuffer header = super.getHeader();
        
        ByteBuffer buf = ByteBuffer.allocate(header.remaining());
        
        buf.put(header);
        buf.putInt(0, buf.capacity());
        buf.flip();
        
        return buf;
    }
    
}
