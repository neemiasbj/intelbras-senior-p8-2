package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.SimpleCard;

public class SetCardListCommand extends HeaderMessage {
    private final byte cardListType;
    
    private final int deviceId;
    
    private final List<SimpleCard> cards;
    
    public SetCardListCommand(byte messageNumber, int devID, byte cardListType) {
        super(messageNumber, (byte) 5, (byte) 3);
        this.deviceId = devID;
        this.cards = new ArrayList<>();
        this.cardListType = cardListType;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public void addCard(SimpleCard card) {
        if (card == null) {
            throw new NullPointerException("Cartão não pode ser nulo. SetCardListCommand");
        } else {
            this.cards.add(card);
        }
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public List<SimpleCard> getCards() {
        return this.cards;
    }
    
    public byte getCardListType() {
        return this.cardListType;
    }
    
}
