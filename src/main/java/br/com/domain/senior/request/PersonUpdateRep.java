package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class PersonUpdateRep extends HeaderMessage {
    private int deviceId;
    
    private Person person;
    
    private byte operation;
    
    private byte bioOperation;
    
    private String cpfResponsible;
    
    public PersonUpdateRep(byte msgNumber) {
        super(msgNumber, (byte) 5, (byte) 31);
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
    
    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public void setBioOperation(byte bioOperation) {
        this.bioOperation = bioOperation;
    }
    
    public byte getBioOperation() {
        return this.bioOperation;
    }
    
    public byte getOperation() {
        return this.operation;
    }
    
    public void setOperation(byte operation) {
        this.operation = operation;
    }
    
    public String getCpfResponsible() {
        return cpfResponsible;
    }
    
    public void setCpfResponsible(String cpfResponsible) {
        this.cpfResponsible = cpfResponsible;
    }
    
}
