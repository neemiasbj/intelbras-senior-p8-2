package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class UndevelopedCommandResponse extends HeaderMessage {
    public UndevelopedCommandResponse(byte messageNumber) {
        super(messageNumber, (byte) 0, (byte) 6);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
