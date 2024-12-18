package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Collection;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.CardUseIdentifier;

public class SetCardIdentifierCommand extends HeaderMessage {
    private final Collection<CardUseIdentifier> cardsUseId;
    
    private final int deviceId;
    
    public SetCardIdentifierCommand(byte messageNumber, Collection<CardUseIdentifier> cardsUseId, int deviceId) {
        super(messageNumber, (byte) 5, (byte) 38);
        this.cardsUseId = cardsUseId;
        this.deviceId = deviceId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public void addCardUseId(CardUseIdentifier cardUseId) {
        this.cardsUseId.add(cardUseId);
    }
    
    public Collection<CardUseIdentifier> getCardUseIds() {
        return this.cardsUseId;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
}
