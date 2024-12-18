package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class NotAuthenticatedResponse extends HeaderMessage {
    public NotAuthenticatedResponse(byte messageNumber) {
        super(messageNumber, (byte) 0, (byte) 3);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
