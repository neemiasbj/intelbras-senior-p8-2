package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestDeviceData extends HeaderMessage {
    public RequestDeviceData(byte msgNumber) {
        super(msgNumber, (byte) 1, (byte) 2);
    }
    
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(13);
        buffer.put(super.getHeader());
        buffer.putInt(0);
        buffer.putInt(0, buffer.position());
        buffer.flip();
        return buffer;
    }
    
}
