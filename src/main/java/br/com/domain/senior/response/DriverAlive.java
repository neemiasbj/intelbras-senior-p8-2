package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class DriverAlive extends HeaderMessage {
    public DriverAlive(byte msgNumber) {
        super(msgNumber, (byte) 0, (byte) 7);
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(10);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
