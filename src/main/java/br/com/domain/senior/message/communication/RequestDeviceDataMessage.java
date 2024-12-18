package br.com.domain.senior.message.communication;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestDeviceDataMessage extends HeaderMessage {
    private final int date;
    
    public RequestDeviceDataMessage(int date, byte messageNumber) {
        super(messageNumber, (byte) 1, (byte) 2);
        this.date = date;
    }
    
    @Override
    public ByteBuffer encode() {
        final int BUFFER_SIZE = 40;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put(super.getHeader());
        buffer.putInt(0);
        buffer.putInt(0, buffer.position());
        buffer.flip();
        return buffer;
    }
    
    public int getDate() {
        return date;
    }
    
}