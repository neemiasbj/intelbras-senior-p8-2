package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.BiometricTemplate;

public class InsertUpdateBiometricDataWithPis extends HeaderMessage {
    private final BiometricData biometricData;
    
    private final long pis;
    
    public InsertUpdateBiometricDataWithPis(byte msgNumber, long pis, BiometricData bioData) {
        super(msgNumber, (byte) 5, (byte) 24);
        this.biometricData = bioData;
        this.pis = pis;
    }
    
    public ByteBuffer encode() {
        int lengthCount = 23;
        
        BiometricTemplate bioTemplate;
        for (Iterator<BiometricTemplate> var2 = this.biometricData.getTemplates()
                                                                  .iterator(); var2.hasNext(); lengthCount += bioTemplate.getTemplate().length + 2) {
            bioTemplate = var2.next();
        }
        
        ByteBuffer buf = ByteBuffer.allocate(lengthCount);
        buf.put(super.getHeader());
        String idPis = String.format("0%d", this.pis);
        buf.put(idPis.getBytes());
        buf.put(this.biometricData.getTechnology().getId());
        buf.put((byte) this.biometricData.getTemplates().size());
        List<BiometricTemplate> templates = this.biometricData.getTemplates();
        Iterator<BiometricTemplate> var5 = templates.iterator();
        
        while (var5.hasNext()) {
            bioTemplate = var5.next();
            byte[] template = bioTemplate.getTemplate();
            buf.putShort((short) template.length);
            buf.put(template);
        }
        
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
}
