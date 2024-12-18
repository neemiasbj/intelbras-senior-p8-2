package br.com.domain.senior.message.communication;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestAuthenticationMessage extends HeaderMessage {
    private static final byte PROTOCOL_VERSION = 0x08;
    
    private static final int HEADER_SIZE = 10;
    
    private final short driverId;
    
    private final byte[] certificate;
    
    public RequestAuthenticationMessage(short driverId, byte[] certificate) {
        super((byte) 1, (byte) 1, (byte) 1);
        this.driverId = driverId;
        this.certificate = certificate;
    }
    
    @Override
    public ByteBuffer encode() {
        int messageLength = HEADER_SIZE + Short.BYTES + this.certificate.length;
        ByteBuffer buf = ByteBuffer.allocate(messageLength);
        
        ByteBuffer header = this.getHeader();
        buf.put(header);
        buf.putShort(driverId);
        buf.put(this.certificate);
        
        buf.putInt(0, buf.position());
        
        buf.flip();
        return buf;
    }
    
    @Override
    protected ByteBuffer getHeader() {
        ByteBuffer buff = ByteBuffer.allocate(HEADER_SIZE);
        buff.putInt(0);
        buff.put(this.getMessageNumber());
        buff.put(PROTOCOL_VERSION);
        buff.put(this.getMessageType());
        buff.put(this.getMessageId());
        buff.putShort((short) HEADER_SIZE);
        buff.flip();
        return buff;
    }
    
}
