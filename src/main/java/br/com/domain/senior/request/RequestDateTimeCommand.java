package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestDateTimeCommand extends HeaderMessage {
    public RequestDateTimeCommand(byte msgNumber) {
        super(msgNumber, (byte) 4, (byte) 4);
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
