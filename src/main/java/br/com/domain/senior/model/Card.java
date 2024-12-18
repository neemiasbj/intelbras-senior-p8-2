package br.com.domain.senior.model;

import br.com.domain.senior.model.enums.CardType;
import br.com.domain.senior.model.enums.Technology;

public class Card {
    private final long idCard;
    
    private byte technology;
    
    private byte situation;
    
    private CardType cardType;
    
    private long startValidity;
    
    private long finishValidity;
    
    private final Person person;
    
    public Card(long idCard, Person person) {
        if (idCard < 0L) {
            throw new IllegalArgumentException("Id do cartão não pode ser negativo. " + idCard);
        } else if (idCard > 999999999999L) {
            throw new IllegalArgumentException("Id do cartão não pode ser maior que 12 dígitos. " + idCard);
        } else {
            this.idCard = idCard;
            if (person == null) {
                throw new IllegalArgumentException("Pessoa não pode ser nula.");
            } else {
                this.person = person;
                this.person.addCard(this);
            }
        }
    }
    
    public long getId() {
        return this.idCard;
    }
    
    public void setSituation(byte situation) {
        if (situation != 0 && situation != 1 && situation != 10) {
            throw new IllegalArgumentException("A situação deve ser 0, 1 ou 10. " + situation);
        } else {
            this.situation = situation;
        }
    }
    
    public byte getSituation() {
        return this.situation;
    }
    
    public void setType(byte type) {
        if (type < 0) {
            throw new IllegalArgumentException("Tipo do cartão não pode ser um numero abaixo de 0");
        } else if (type != 99 && type > 13) {
            throw new IllegalArgumentException("Tipo de cartão invalido: " + type);
        } else {
            this.cardType = CardType.getCardType(type);
        }
    }
    
    public CardType getType() {
        return this.cardType;
    }
    
    public void setTechnology(byte technology) {
        if (technology >= Technology.UNKNOWN.value() && technology <= Technology.SMART.value()) {
            this.technology = technology;
        } else {
            throw new IllegalStateException("Tecnologia do cartão deve ser um valor de 0 a 3." + technology);
        }
    }
    
    public byte getTechnology() {
        return this.technology;
    }
    
    public void setStartValidity(long startValidity) {
        if (startValidity < 0L) {
            throw new IllegalArgumentException("Início da validade do cartão não pode ser menor que 0. " + startValidity);
        } else {
            this.startValidity = startValidity;
        }
    }
    
    public long getStartValidity() {
        return this.startValidity;
    }
    
    public void setFinishValidity(long finishValidity) {
        if (finishValidity < 0L) {
            throw new IllegalArgumentException("Termino da validade do cartão não pode ser menor que 0. " + finishValidity);
        } else {
            this.finishValidity = finishValidity;
        }
    }
    
    public long getFinishValidity() {
        return this.finishValidity;
    }
    
    public Person getPerson() {
        return this.person;
    }
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            Card other = (Card) obj;
            return this.idCard == other.idCard;
        }
    }
    
    public int hashCode() {
        return String.valueOf(this.idCard).hashCode();
    }
    
    public String toString() {
        return "Card (idCard=" + this.idCard + ")";
    }
    
}
