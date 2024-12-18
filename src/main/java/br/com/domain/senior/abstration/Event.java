package br.com.domain.senior.abstration;

import java.util.Date;

import br.com.domain.senior.model.enums.DeviceCommType;

public abstract class Event {
    private DeviceCommType statusComm = DeviceCommType.OFFLINE;
    
    private Date date = new Date(0L);
    
    private short gmt;
    
    private Device device;
    
    public Date getDate() {
        return new Date(date.getTime());
    }
    
    protected void setDate(Date date) {
        this.date = (date != null) ? new Date(date.getTime()) : new Date(0L);
    }
    
    public short getGmt() {
        return gmt;
    }
    
    protected void setGmt(short gmt) {
        this.gmt = gmt;
    }
    
    public DeviceCommType getStatusComm() {
        return statusComm;
    }
    
    protected void setStatusComm(DeviceCommType statusComm) {
        this.statusComm = statusComm;
    }
    
    public Device getDevice() {
        return device;
    }
    
    protected void setDevice(Device device) {
        this.device = device;
    }
    
    @Override
    public String toString() {
        return String.format("Event(deviceId=%d, statusComm=%s, date=%s)", (device != null ? device.getId() : null), statusComm, date);
    }
    
}
