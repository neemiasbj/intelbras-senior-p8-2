package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.senior.abstration.HeaderMessage;

public class DeviceStatusResponse extends HeaderMessage {
    private static final Logger logger = LoggerFactory.getLogger(DeviceStatusResponse.class);
    
    private final String[] properties;
    
    public DeviceStatusResponse(byte messageNumber, String[] properties) {
        super(messageNumber, (byte) 6, (byte) 1);
        this.properties = properties;
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        
        try {
            int sizePos = buf.position();
            buf.put((byte) 0);
            int count = this.parseProperties(this.properties, buf);
            buf.put(sizePos, (byte) count);
        } catch (ParseException var5) {
            logger.error(var5.toString());
            buf.clear();
            return null;
        }
        
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
    private int parseProperties(String[] prop, ByteBuffer buf) throws ParseException {
        int ret = 0;
        String[] var4 = prop;
        int var5 = prop.length;
        int var6 = 0;
        
        while (var6 < var5) {
            String property = var4[var6];
            String[] parsed = property.split("=");
            if (parsed.length != 2) {
                throw new ParseException("Propriedade com o formato incorreto : " + property, 0);
            }
            
            String name = parsed[0];
            String value = parsed[1];
            if (name.length() != 0 && value.length() != 0) {
                if (name.length() <= 255 && value.length() <= 255) {
                    buf.put((byte) name.length());
                    buf.put(name.getBytes());
                    buf.put((byte) value.length());
                    buf.put(value.getBytes());
                    ++ret;
                    ++var6;
                    continue;
                }
                
                throw new ParseException("Propriedade não pode ser maior que 255 caracteres por membro (nome/valor): " + property, 0);
            }
            
            throw new ParseException("Propriedade não pode ser vazia: " + property, 0);
        }
        
        return ret;
    }
    
}
