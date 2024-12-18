package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.senior.service.message.CountNumberMessage;

public class SendAuthentication extends HeaderMessage {
    private final byte[] certificado;
    
    private final short driver;
    
    public SendAuthentication(byte[] cert, short driver) {
        super(CountNumberMessage.getMessageNumber(), (byte) 1, (byte) 1);
        this.certificado = cert;
        this.driver = driver;
    }
    
    public ByteBuffer encode() {
        int totalSize = getHeader().remaining() + Short.BYTES + certificado.length;
        
        ByteBuffer buf = ByteBuffer.allocate(totalSize);
        
        ByteBuffer header = this.getHeader();
        buf.put(header);
        buf.putShort(this.driver);
        buf.put(this.certificado);
        
        buf.putInt(0, buf.position());
        
        buf.flip();
        return buf;
    }
    
    @Override
    protected final ByteBuffer getHeader() {
        ByteBuffer buff = ByteBuffer.allocate(10);
        buff.putInt(0);
        buff.put(this.getMessageNumber());
        buff.put((byte) 8);
        buff.put(this.getMessageType());
        buff.put(this.getMessageId());
        buff.putShort((short) 10);
        buff.flip();
        return buff;
    }
    
}
