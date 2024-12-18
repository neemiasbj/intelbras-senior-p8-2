package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.domain.senior.abstration.HeaderMessage;

public class AuthenticationSuccessfull extends HeaderMessage {
    private final Map<String, String> extensibleProperties = new HashMap<>();
    
    public AuthenticationSuccessfull(byte msgNumber) {
        super(msgNumber, (byte) 1, (byte) 1);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public void addExtensibleProperty(String identification, String data) {
        if (identification != null && data != null) {
            this.extensibleProperties.put(identification, data);
        } else {
            throw new NullPointerException("Idenficador ou o valor da propriedade extensivel não podem ser nulos");
        }
    }
    
    public Map<String, String> getExtensibleProperties() {
        return Collections.unmodifiableMap(this.extensibleProperties);
    }
    
}
