package br.com.domain.intelbras.abstraction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Model for API device request")
public abstract class DeviceAbstract {
    
    @NotBlank(message = "Device IP address cannot be blank")
    @Schema(description = "IP address of the device", example = "192.168.0.1")
    private String deviceIpAddress;
    
    public String getDeviceIpAddress() {
        return deviceIpAddress;
    }
    
    public void setDeviceIpAddress(String deviceIpAddress) {
        this.deviceIpAddress = deviceIpAddress;
    }
    
    protected DeviceAbstract(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
        super();
        this.deviceIpAddress = deviceIpAddress;
    }
    
    @Override
    public String toString() {
        return "[deviceIpAddress=" + deviceIpAddress + "]";
    }
    
}