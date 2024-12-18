package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class UnsuportedProtocolVersionResponse extends HeaderMessage {
    public UnsuportedProtocolVersionResponse(byte messageNumber) {
        super(messageNumber, (byte) 0, (byte) 2);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
