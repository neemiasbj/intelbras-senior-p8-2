package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RECV extends HeaderMessage {
    public RECV(byte msgNumber) {
        super(msgNumber, (byte) 0, (byte) 3);
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(9);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
