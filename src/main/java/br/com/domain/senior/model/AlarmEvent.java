package br.com.domain.senior.model;

import java.util.Date;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.Event;
import br.com.domain.senior.model.enums.AlarmType;
import br.com.domain.senior.model.enums.DeviceCommType;

public class AlarmEvent extends Event {
    private final boolean active;
    
    private final AlarmType type;
    
    private final byte tie;
    
    public AlarmEvent(Device device, AlarmType type, DeviceCommType currentStatus, boolean active, Date eventDate, short gmt) {
        this(device, type, currentStatus, active, eventDate, gmt, (byte) -1);
    }
    
    public AlarmEvent(Device device, AlarmType type, DeviceCommType currentStatus, boolean active, Date eventDate, short gmt, byte tie) {
        this.setDevice(device);
        this.setStatusComm(currentStatus);
        this.setDate(eventDate);
        this.setGmt(gmt);
        this.active = active;
        this.type = type;
        this.tie = tie;
    }
    
    public AlarmType getType() {
        return this.type;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public byte getTie() {
        return this.tie;
    }
    
}
