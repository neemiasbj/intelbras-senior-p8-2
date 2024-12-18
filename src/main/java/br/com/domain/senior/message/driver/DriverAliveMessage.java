package br.com.domain.senior.message.driver;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class DriverAliveMessage extends HeaderMessage {
    public DriverAliveMessage(byte msgNumber) {
        super(msgNumber, (byte) 0, (byte) 7);
    }
    
    @Override
    public ByteBuffer encode() {
        final int BUFFER_SIZE = 10;
        ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}