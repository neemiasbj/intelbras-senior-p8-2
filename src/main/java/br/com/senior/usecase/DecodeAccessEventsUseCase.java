package br.com.senior.usecase;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.request.AccessValidationTimeoutCommand;
import br.com.domain.senior.response.AccessValidationResponse;
import br.com.util.Utils;

public class DecodeAccessEventsUseCase {
    
    public static HeaderMessage decodeAccessValidationResponse(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        byte returnType = buffer.get();
        byte subReturnType = buffer.get();
        byte cardType = buffer.get();
        short pendencyCount = buffer.getShort();
        
        for (int i = 0; i < pendencyCount; ++i) {
            buffer.getShort();
            short fieldLength = buffer.getShort();
            byte[] fieldValue = new byte[fieldLength];
            buffer.get(fieldValue);
        }
        
        AccessValidationResponse ret = new AccessValidationResponse(messageNumber, returnType, subReturnType, cardType);
        if (returnType == 0) {
            String messageS = Utils.getString(buffer, 32);
            ret.setMessage(messageS);
            byte levelAllowed = buffer.get();
            ret.setPersonLevel(levelAllowed);
            byte creditRange = (byte) (buffer.get() & 255);
            ret.setAccessCreditRange(creditRange);
            boolean verifyBiometry = buffer.get() == 1;
            ret.setVerifyBiometry(verifyBiometry);
            byte friskPerson = (byte) (buffer.get() & 255);
            ret.setFriskPerson(friskPerson);
            String personID = Utils.getString(buffer, 23);
            ret.setPersonId(personID);
            return ret;
        } else {
            long startRemoval;
            if (subReturnType == 4) {
                startRemoval = buffer.getLong();
                ret.setFinishValidity(startRemoval);
                return ret;
            } else if (subReturnType == 6) {
                startRemoval = buffer.getLong();
                long endRemoval = buffer.getLong();
                ret.setStartRemoval(startRemoval);
                ret.setEndRemoval(endRemoval);
                return ret;
            } else {
                return ret;
            }
        }
    }
    
    public static HeaderMessage decodeAccessValidationTimeout(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int cardReaderDeviceId = buffer.getInt();
        return new AccessValidationTimeoutCommand(messageNumber, cardReaderDeviceId);
    }
    
}
