package br.com.domain.senior.model;

import br.com.domain.senior.model.enums.CardType;

public class CardUseIdentifier {
    private final byte identifier;
    
    private CardType cardUse;
    
    public CardUseIdentifier(byte identifier) {
        this.identifier = identifier;
    }
    
    public byte getIdentifier() {
        return this.identifier;
    }
    
    public void setCardUse(CardType cardUse) {
        this.cardUse = cardUse;
    }
    
    public CardType getCardUse() {
        return this.cardUse;
    }
    
    public int hashCode() {
        // int prime = true;
        int result = 1;
        result = 31 * result + (this.cardUse == null ? 0 : this.cardUse.hashCode());
        result = 31 * result + this.identifier;
        return result;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            CardUseIdentifier other = (CardUseIdentifier) obj;
            if (this.cardUse != other.cardUse) {
                return false;
            } else {
                return this.identifier == other.identifier;
            }
        }
    }
    
}
