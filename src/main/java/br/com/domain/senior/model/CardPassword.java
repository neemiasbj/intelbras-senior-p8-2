package br.com.domain.senior.model;

public class CardPassword {
    private final long cardNumber;
    
    private final byte[] password;
    
    public CardPassword(long cardNumber, byte[] password) {
        this.cardNumber = cardNumber;
        this.password = password;
    }
    
    public long getCardNumber() {
        return this.cardNumber;
    }
    
    public byte[] getPassword() {
        return this.password;
    }
    
}
