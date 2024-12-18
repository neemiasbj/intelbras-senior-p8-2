package br.com.domain.senior.model;

import java.util.Date;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.Event;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.StatusType;

public class StatusEvent extends Event {
    private final StatusType type;
    
    public StatusEvent(Device device, StatusType type, DeviceCommType currentStatus, Date eventDate, short gmt) {
        this.setDevice(device);
        this.setStatusComm(currentStatus);
        this.setDate(eventDate);
        this.setGmt(gmt);
        if (type == null) {
            throw new NullPointerException("O tipo de status não pode ser nulo.");
        } else {
            this.type = type;
        }
    }
    
    public StatusType getType() {
        return this.type;
    }
    
}
