package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class BiometricDataResponse extends HeaderMessage {
    private final List<Person> personList;
    
    public BiometricDataResponse(byte msgNumber, List<Person> personList) {
        super(msgNumber, (byte) 5, (byte) 16);
        this.personList = personList;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public List<Person> getPersonList() {
        return this.personList;
    }
    
}
