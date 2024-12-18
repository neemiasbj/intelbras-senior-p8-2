package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class NACK extends HeaderMessage {
    private final String errorMessage;
    
    private final byte errorType;
    
    public NACK(byte msgNumber, String errorMessage, byte errorType) {
        super(msgNumber, (byte) 0, (byte) 1);
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }
    
    public ByteBuffer encode() {
        ByteBuffer header = super.getHeader();
        byte[] errorBytes = this.errorMessage.getBytes();
        
        int requiredCapacity = header.remaining() + 2 + errorBytes.length + 1;
        ByteBuffer buf = ByteBuffer.allocate(requiredCapacity);
        
        buf.put(header);
        buf.putShort((short) errorBytes.length);
        buf.put(errorBytes);
        buf.put(this.errorType);
        
        buf.flip();
        return buf;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(msgNumber = " + this.getMessageNumber() + ", msg = " + this.errorMessage + ")";
    }
    
}
