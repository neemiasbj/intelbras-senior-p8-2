package br.com.domain.senior.abstration;

import java.nio.ByteBuffer;

public abstract class HeaderMessage implements Encodable {
    public final byte messageNumber;
    
    public final byte messageType;
    
    public final byte messageId;
    
    protected HeaderMessage(byte msgNumber, byte messageType, byte messageId) {
        this.messageNumber = msgNumber;
        this.messageType = messageType;
        this.messageId = messageId;
    }
    
    public final byte getMessageNumber() {
        return this.messageNumber;
    }
    
    protected ByteBuffer getHeader() {
        ByteBuffer buff = ByteBuffer.allocate(9);
        buff.putInt(0);
        buff.put(this.getMessageNumber());
        buff.put(this.getMessageType());
        buff.put(this.getMessageId());
        buff.putShort((short) 9);
        buff.flip();
        return buff;
    }
    
    protected final byte getMessageId() {
        return this.messageId;
    }
    
    protected final byte getMessageType() {
        return this.messageType;
    }
    
    public String toString() {
        return this.getClass().getSimpleName() + "(msgNumber = " + this.getMessageNumber() + ", messageType = " + this.messageType + ", messageId = "
               + this.messageId + ")";
    }
    
    public abstract ByteBuffer encode();
    
}
