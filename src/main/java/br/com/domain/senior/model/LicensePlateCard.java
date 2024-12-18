package br.com.domain.senior.model;

public class LicensePlateCard {
    private final long cardNumber;
    
    private final byte cardTechnology;
    
    public LicensePlateCard(long cardNumber, byte cardTechnology) {
        this.cardNumber = cardNumber;
        this.cardTechnology = cardTechnology;
    }
    
    public long getCardNumber() {
        return this.cardNumber;
    }
    
    public byte getCardTechnology() {
        return this.cardTechnology;
    }
    
    public String toString() {
        return String.format("LicensePlateCardDatagram[ Número = %d , Tecnologia = %d]", this.cardNumber, Integer.valueOf(this.cardTechnology));
    }
    
}
