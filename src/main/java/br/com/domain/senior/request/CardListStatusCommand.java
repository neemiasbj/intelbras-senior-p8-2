package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class CardListStatusCommand extends HeaderMessage {
    private final int deviceID;
    
    private final byte cardListType;
    
    public CardListStatusCommand(byte messageNumber, int devID, byte cardListType) {
        super(messageNumber, (byte) 5, (byte) 9);
        this.deviceID = devID;
        this.cardListType = cardListType;
    }
    
    public int getDeviceId() {
        return this.deviceID;
    }
    
    public byte getCardListType() {
        return this.cardListType;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
}
