package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.Person;

public class BiometricPersonListResponse extends HeaderMessage {
    private final Map<Integer, List<Person>> peopleOnDevices;
    
    public BiometricPersonListResponse(byte msgNumber, Map<Integer, List<Person>> peopleOnDevices) {
        super(msgNumber, (byte) 5, (byte) 21);
        this.peopleOnDevices = peopleOnDevices;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.put(super.getHeader());
        Set<Entry<Integer, List<Person>>> entrySet = this.peopleOnDevices.entrySet();
        buffer.putInt(entrySet.size());
        Iterator<Entry<Integer, List<Person>>> var3 = entrySet.iterator();
        
        while (var3.hasNext()) {
            Entry<Integer, List<Person>> entry = var3.next();
            buffer.putInt(entry.getKey());
            List<Person> people = entry.getValue();
            buffer.putInt(people.size());
            Iterator<Person> var6 = people.iterator();
            
            while (var6.hasNext()) {
                Person person = var6.next();
                buffer.put(person.getIdPerson().getBytes());
            }
        }
        
        buffer.putInt(0, buffer.position());
        buffer.flip();
        return buffer;
    }
    
}
