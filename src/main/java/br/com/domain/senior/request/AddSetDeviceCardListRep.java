package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class AddSetDeviceCardListRep extends HeaderMessage {
    private int deviceId;
    
    private List<Person> people = new ArrayList<>();
    
    private boolean overwrite;
    
    public AddSetDeviceCardListRep(byte msgNumber) {
        super(msgNumber, (byte) 5, (byte) 32);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    
    public List<Person> getPeople() {
        return this.people;
    }
    
    public void setPeople(List<Person> person) {
        this.people = person;
    }
    
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
    
    public boolean isOverwrite() {
        return this.overwrite;
    }
    
}
