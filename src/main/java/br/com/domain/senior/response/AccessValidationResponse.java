package br.com.domain.senior.response;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.enums.AccessType;
import br.com.util.DateFormatUtil;

public class AccessValidationResponse extends HeaderMessage {
    private final boolean accessGranted;
    
    private AccessType subReturnType;
    
    private String message;
    
    private long finishValidity;
    
    private long startRemoval;
    
    private long endRemoval;
    
    private byte personLevel;
    
    private final byte cardType;
    
    private byte accessCreditRange;
    
    private boolean verifyBiometry;
    
    private byte friskPerson;
    
    private String personId;
    
    public AccessValidationResponse(byte messageNumber, byte returnType, byte subReturnType, byte cardType) {
        super(messageNumber, (byte) 2, (byte) 2);
        this.accessGranted = returnType == 0;
        this.subReturnType = AccessType.valueOf(subReturnType);
        this.cardType = cardType;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public boolean isAccessGranted() {
        return this.accessGranted;
    }
    
    public AccessType getSubReturnType() {
        return this.subReturnType;
    }
    
    public void setSubReturnType(AccessType subReturnType) {
        this.subReturnType = subReturnType;
    }
    
    public void setMessage(String message) {
        if (message == null) {
            throw new NullPointerException("Mensagem não pode ser nula. AccessValidationResponse");
        } else {
            if (this.accessGranted && message.length() <= 32) {
                this.message = message.trim();
            }
            
        }
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public void setAccessCreditRange(byte creditRange) {
        this.accessCreditRange = creditRange;
    }
    
    public byte getAccessCreditRange() {
        return this.accessCreditRange;
    }
    
    public void setFinishValidity(long finishValidity) {
        if (!this.accessGranted && this.subReturnType == AccessType.ACCESS_DENIED_VALIDITY) {
            this.finishValidity = finishValidity;
        }
        
    }
    
    public long getFinishValidity() {
        return this.finishValidity;
    }
    
    public void setStartRemoval(long startRemoval) {
        if (!this.accessGranted && this.subReturnType == AccessType.ACCESS_DENIED_INTERVAL_REMOVAL) {
            this.startRemoval = startRemoval;
        }
        
    }
    
    public long getStartRemoval() {
        return this.startRemoval;
    }
    
    public void setEndRemoval(long endRemoval) {
        if (!this.accessGranted && this.subReturnType == AccessType.ACCESS_DENIED_INTERVAL_REMOVAL) {
            this.endRemoval = endRemoval;
        }
        
    }
    
    public long getEndRemoval() {
        return this.endRemoval;
    }
    
    public void setPersonLevel(byte level) {
        this.personLevel = level;
    }
    
    public byte getPersonLevel() {
        return this.personLevel;
    }
    
    public void setVerifyBiometry(boolean verifyBiometry) {
        this.verifyBiometry = verifyBiometry;
    }
    
    public boolean isVerifyBiometry() {
        return this.verifyBiometry;
    }
    
    public void setFriskPerson(byte friskPerson) {
        this.friskPerson = friskPerson;
    }
    
    public byte getFriskPerson() {
        return this.friskPerson;
    }
    
    public byte getCardType() {
        return this.cardType;
    }
    
    @Override
    public String toString() {
        String ret = super.toString() + " " + this.subReturnType.getDescription();
        if (this.accessGranted) {
            ret = ret + (this.message != null && this.message.length() != 0 ? ", mensagem: " + this.message + ".\n" : ".");
        }
        
        if (this.subReturnType == AccessType.ACCESS_DENIED_VALIDITY) {
            return ret + ", final da validade: " + DateFormatUtil.formatDateTimeToString(this.finishValidity);
        } else {
            return this.subReturnType == AccessType.ACCESS_DENIED_INTERVAL_REMOVAL ? ret + ", início do afastamento: "
                                                                                     + DateFormatUtil.formatDateTimeToString(this.startRemoval)
                                                                                     + ", final do afastamento: "
                                                                                     + DateFormatUtil.formatDateTimeToString(this.endRemoval)
                                                                                   : ret;
        }
    }
    
}
