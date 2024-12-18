package br.com.senior.usecase;

import java.nio.ByteBuffer;

import br.com.domain.senior.response.ACK;
import br.com.domain.senior.response.NACK;
import br.com.domain.senior.response.NotAuthenticatedResponse;
import br.com.domain.senior.response.UndevelopedCommandResponse;
import br.com.domain.senior.response.UnsuportedProtocolVersionResponse;
import br.com.util.Utils;

public class DecodeResponseUseCase {
    
    private static final byte PROTOCOL_ERROR = 1;
    
    private static final String ERROR_MSG = "Mensagem não implementada!";
    
    public static NACK returnMessage(byte messageNumber) {
        return new NACK(messageNumber, ERROR_MSG, PROTOCOL_ERROR);
    }
    
    public static NACK decodeNack(byte messageNumber, ByteBuffer buffer) {
        
        String errorString = getErrorString(buffer);
        return new NACK(messageNumber, errorString, (byte) 0);
        
    }
    
    public static ACK decodeAck(byte messageNumber) {
        return new ACK(messageNumber);
    }
    
    public static UnsuportedProtocolVersionResponse decodeUnsuportedProtocolVersionResponse(byte messageNumber) {
        return new UnsuportedProtocolVersionResponse(messageNumber);
    }
    
    public static NotAuthenticatedResponse decodeNotAuthenticatedResponse(byte messageNumber) {
        return new NotAuthenticatedResponse(messageNumber);
    }
    
    public static UndevelopedCommandResponse decodeUndevelopedCommandResponse(byte messageNumber) {
        return new UndevelopedCommandResponse(messageNumber);
    }
    
    private static String getErrorString(ByteBuffer buffer) {
        buffer.getShort();
        short length = buffer.getShort();
        return Utils.getString(buffer, length);
    }
    
}
