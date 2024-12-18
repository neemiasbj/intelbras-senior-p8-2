package br.com.domain.senior.request;

import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;

public class UpdateFirmwareCommand extends HeaderMessage {
    private final int deviceId;
    
    public UpdateFirmwareCommand(byte msgNumber, int deviceId) {
        super(msgNumber, (byte) 6, (byte) 16);
        this.deviceId = deviceId;
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public int getDeviceId() {
        return this.deviceId;
    }
    
}
