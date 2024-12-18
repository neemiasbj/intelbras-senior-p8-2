package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class IsAliveCommand extends HeaderMessage {
    public IsAliveCommand(byte msgNumber) {
        super(msgNumber, (byte) 6, (byte) 4);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
