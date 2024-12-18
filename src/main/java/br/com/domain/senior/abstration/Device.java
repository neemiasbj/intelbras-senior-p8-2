package br.com.domain.senior.abstration;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.ModuleController;
import br.com.domain.senior.model.enums.DeviceCommType;

public abstract class Device {
    private String description;
    
    private int id;
    
    private int childId;
    
    private final Map<String, String> extensibleProperties = new HashMap<>();
    
    private DeviceCommType status = DeviceCommType.OFFLINE;
    
    private ModuleController parent;
    
    protected Device() {
        this.status = DeviceCommType.OFFLINE;
    }
    
    public Date getDate() {
        return new Date(System.currentTimeMillis());
    }
    
    public void addExtensibleData(String identification, String value) {
        extensibleProperties.put(identification, value);
    }
    
    public Map<String, String> getExtensibleData() {
        return Collections.unmodifiableMap(extensibleProperties);
    }
    
    public Map<String, String> getExtensibleDataTeste() {
        return extensibleProperties;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public DeviceCommType getStatus() {
        return status;
    }
    
    public void setStatus(DeviceCommType status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getChildId() {
        return childId;
    }
    
    public void setChildId(int childId) {
        this.childId = childId;
    }
    
    public ModuleController getParent() {
        return parent;
    }
    
    public void setParent(ModuleController parent) {
        this.parent = parent;
    }
    
    public ManagerDevice getManager() {
        Device currentDevice = this;
        while (currentDevice != null) {
            if (currentDevice instanceof ManagerDevice managerDevice) {
                return managerDevice;
            }
            currentDevice = currentDevice.getParent();
        }
        return null;
    }
    
    public boolean isStarted() {
        return getParent() != null && getParent().isStarted();
    }
    
    @Override
    public String toString() {
        return String.format("Device(id=%d, childId=%d, status=%s, description=%s)", id, childId, status, description != null ? description : "N/A");
    }
    
}
