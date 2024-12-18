package br.com.domain.senior.model;

import java.util.Date;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.Event;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.ResourceStatusType;

public class ResourceStatusEvent extends Event {
    private final ResourceStatusType type;
    
    private final byte resourcePercentage;
    
    public ResourceStatusEvent(Device device, ResourceStatusType type, DeviceCommType currentStatus, Date eventDate, short gmt, byte percentageLeft) {
        this.setDevice(device);
        this.setStatusComm(currentStatus);
        this.setDate(eventDate);
        this.setGmt(gmt);
        if (type == null) {
            throw new NullPointerException("O tipo de recurso não pode ser nulo.");
        } else {
            this.type = type;
            this.resourcePercentage = percentageLeft;
        }
    }
    
    public ResourceStatusType getType() {
        return this.type;
    }
    
    public byte getResourcePercentageLeft() {
        return this.resourcePercentage;
    }
    
}
