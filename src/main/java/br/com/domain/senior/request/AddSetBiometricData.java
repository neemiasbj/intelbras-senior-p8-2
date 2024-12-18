package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.List;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class AddSetBiometricData extends HeaderMessage {
    private final List<Person> personList;
    
    private final boolean overwrite;
    
    private final List<Device> deviceList;
    
    public AddSetBiometricData(byte msgNumber, List<Person> personList, boolean overwrite, List<Device> deviceList) {
        super(msgNumber, (byte) 5, (byte) 36);
        this.personList = personList;
        this.overwrite = overwrite;
        this.deviceList = deviceList;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public List<Person> getPersonList() {
        return this.personList;
    }
    
    public boolean isOverwrite() {
        return this.overwrite;
    }
    
    public List<Device> getDeviceList() {
        return this.deviceList;
    }
    
}
