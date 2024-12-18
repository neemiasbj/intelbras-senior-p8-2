package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.BiometricTemplate;
import br.com.domain.senior.model.Person;

public class InsertUpdateBiometricData extends HeaderMessage {
    private final Person person;
    
    private final BiometricData biometricData;
    
    public InsertUpdateBiometricData(byte msgNumber, Person person, BiometricData bioData) {
        super(msgNumber, (byte) 5, (byte) 17);
        this.person = person;
        this.biometricData = bioData;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(128 + this.biometricData.getTemplates().size() * 512);
        buffer.put(super.getHeader());
        // int size = true;
        byte[] personId = new byte[23];
        byte[] currentId = this.person.getIdPerson().getBytes();
        int destPos = currentId.length >= 23 ? 0 : 23 - currentId.length;
        int copyLength = Math.min(currentId.length, 23);
        System.arraycopy(currentId, 0, personId, destPos, copyLength);
        buffer.put(personId);
        buffer.put(this.biometricData.getTechnology().getId());
        buffer.put((byte) this.biometricData.getTemplates().size());
        List<BiometricTemplate> templates = this.biometricData.getTemplates();
        Iterator<BiometricTemplate> var8 = templates.iterator();
        
        while (var8.hasNext()) {
            BiometricTemplate template = var8.next();
            byte[] templateData = template.getTemplate();
            buffer.putShort((short) templateData.length);
            buffer.put(templateData);
        }
        
        buffer.putInt(0, buffer.position());
        buffer.flip();
        return buffer;
    }
    
}
