package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class DeletePersonBiometry extends HeaderMessage {
    private final int device;
    
    private final List<Person> personList;
    
    public DeletePersonBiometry(byte msgNumber, int device, List<Person> personList) {
        super(msgNumber, (byte) 5, (byte) 37);
        this.device = device;
        this.personList = personList;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public List<Person> getPersonList() {
        return this.personList;
    }
    
    public int getDevice() {
        return this.device;
    }
    
}
