package br.com.senior.usecase;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.request.ActivateEmergencyCommand;
import br.com.domain.senior.request.DeactivateEmergencyCommand;
import br.com.domain.senior.request.RequestDeviceStatusCommand;
import br.com.domain.senior.request.UnlockDeviceCommand;
import br.com.domain.senior.response.LockDeviceCommand;

public class DecodeDeviceInteractionUseCase {
    
    public static HeaderMessage decodeDeactivateEmergency(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int deviceID = buffer.getInt();
        return new DeactivateEmergencyCommand(messageNumber, deviceID);
    }
    
    public static HeaderMessage decodeActivateEmergency(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int deviceID = buffer.getInt();
        return new ActivateEmergencyCommand(messageNumber, deviceID);
    }
    
    public static HeaderMessage decodeUnlockDevice(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int deviceID = buffer.getInt();
        return new UnlockDeviceCommand(messageNumber, deviceID);
    }
    
    public static HeaderMessage decodeLockDevice(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        int deviceID = buffer.getInt();
        return new LockDeviceCommand(messageNumber, deviceID);
    }
    
    public static HeaderMessage decodeRequestDeviceStatus(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        return new RequestDeviceStatusCommand(messageNumber, buffer.getInt());
    }
    
}
