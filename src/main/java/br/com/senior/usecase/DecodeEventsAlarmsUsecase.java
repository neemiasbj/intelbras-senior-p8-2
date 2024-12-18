package br.com.senior.usecase;

import java.nio.ByteBuffer;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.request.NotifyDeviceAlarmed;
import br.com.domain.senior.request.NotifyDeviceEvent;
import br.com.domain.senior.request.NotifyDeviceResourceStatus;

public class DecodeEventsAlarmsUsecase {
    
    public static HeaderMessage decodeNotifyDeviceAlarmed(ByteBuffer buffer, byte msgNumber) {
        buffer.getShort();
        
        int deviceID = buffer.getInt();
        Date eventDate = new Date(buffer.getInt() * 1000L);
        short gmt = buffer.getShort();
        DeviceCommType generatedOnline = DeviceCommType.fromValue(buffer.get());
        boolean active = buffer.get() == 0;
        return new NotifyDeviceAlarmed(msgNumber, deviceID, eventDate, gmt, generatedOnline, active);
    }
    
    public static HeaderMessage decodeNotifyDeviceEvent(ByteBuffer buffer, byte msgNumber) {
        buffer.getShort();
        int devID = buffer.getInt();
        Date eventDate = new Date(buffer.getInt() * 1000L);
        short gmt = buffer.getShort();
        DeviceCommType generatedOnline = DeviceCommType.fromValue(buffer.get());
        byte eventType = buffer.get();
        
        return new NotifyDeviceEvent(msgNumber, devID, eventDate, gmt, generatedOnline, eventType);
    }
    
    public static HeaderMessage decodeNotifyDeviceResourceStatus(ByteBuffer buffer, byte msgNumber) {
        buffer.getShort();
        int devID = buffer.getInt();
        Date eventDate = new Date(buffer.getInt() * 1000L);
        short gmt = buffer.getShort();
        DeviceCommType generatedOnline = DeviceCommType.fromValue(buffer.get());
        byte resourcePercent = buffer.get();
        byte resourceType = buffer.get();
        
        return new NotifyDeviceResourceStatus(msgNumber, devID, eventDate, gmt, generatedOnline, resourcePercent, resourceType);
    }
    
}
