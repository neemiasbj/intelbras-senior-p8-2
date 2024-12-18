package br.com.senior.usecase;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.request.DateTimeCommand;
import br.com.domain.senior.request.IsAliveCommand;
import br.com.domain.senior.response.DateTimeResponse;

public class DecodeDeviceConfigUseCase {
    
    public static HeaderMessage decodeDateTimeCommand(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int deviceID = buffer.getInt();
        return new DateTimeCommand(messageNumber, deviceID);
    }
    
    public static HeaderMessage decodeDateTimeResponse(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        Date date = new Date(buffer.getInt() * 1000L);
        return new DateTimeResponse(messageNumber, date);
    }
    
    public static HeaderMessage decodeIsAliveCommand(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        return new IsAliveCommand(messageNumber);
    }
    
}
