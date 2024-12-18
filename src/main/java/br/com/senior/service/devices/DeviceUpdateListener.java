package br.com.senior.service.devices;

import java.util.Collection;

import br.com.domain.senior.model.ManagerDevice;

public interface DeviceUpdateListener {
    void onDeviceUpdate(Collection<ManagerDevice> devices);
    
}
