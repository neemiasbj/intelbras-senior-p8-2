package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class RequestBiometricData extends HeaderMessage {
    private final String personId;
    
    private final byte technologyId;
    
    public RequestBiometricData(byte msgNumber, String personId, byte technologyId) {
        super(msgNumber, (byte) 5, (byte) 16);
        this.personId = personId;
        this.technologyId = technologyId;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.put(super.getHeader());
        // int size = true;
        byte[] personArray = new byte[23];
        if (this.personId != null) {
            int personIdLength = this.personId.length() > 23 ? 23 : this.personId.length();
            System.arraycopy(this.personId.getBytes(), 0, personArray, 23 - personIdLength, personIdLength);
        }
        
        buffer.put(personArray);
        buffer.put(this.technologyId);
        buffer.putInt(0);
        buffer.putInt(0, buffer.position());
        buffer.flip();
        return buffer;
    }
    
}
