package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class AccessValidationTimeoutCommand extends HeaderMessage {
    private final int cardReaderId;
    
    public AccessValidationTimeoutCommand(byte messageNumber, int cardReaderId) {
        super(messageNumber, (byte) 2, (byte) 2);
        this.cardReaderId = cardReaderId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public int getCardReaderId() {
        return this.cardReaderId;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", timeout occured on reader #" + this.cardReaderId;
    }
    
}
