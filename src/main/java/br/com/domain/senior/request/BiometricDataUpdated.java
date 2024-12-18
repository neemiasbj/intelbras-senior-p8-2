package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.Person;

public class BiometricDataUpdated extends HeaderMessage {
    private final Person person;
    
    private final BiometricData biometricData;
    
    public BiometricDataUpdated(byte messageNumber, Person person, BiometricData bioData) {
        super(messageNumber, (byte) 5, (byte) 12);
        this.person = person;
        this.biometricData = bioData;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public Person getPerson() {
        return this.person;
    }
    
    public BiometricData getBiometricData() {
        return this.biometricData;
    }
    
}
