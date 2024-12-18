package br.com.domain.intelbras.model;

import br.com.domain.intelbras.model.enums.AccessDirectionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Model for webhook request for fast online access")
public class AccessValidateModel {
    @NotBlank(message = "Device IP address cannot be blank")
    @Schema(description = "IP address of the device", example = "192.168.0.1")
    private String deviceIpAddress;
    
    @Schema(description = "Registry identifier", example = "98765")
    private String registry;
    
    @Schema(description = "Direction of access", example = "ENTRANCE")
    private AccessDirectionEnum direction;
    
    public String getDeviceIpAddress() {
        return deviceIpAddress;
    }
    
    public void setDeviceIpAddress(String deviceIpAddress) {
        this.deviceIpAddress = deviceIpAddress;
    }
    
    public AccessDirectionEnum getDirection() {
        return direction;
    }
    
    public void setDirection(AccessDirectionEnum direction) {
        this.direction = direction;
    }
    
    public String getRegistry() {
        return registry;
    }
    
    public void setRegistry(String registry) {
        this.registry = registry;
    }
    
    @Override
    public String toString() {
        return "[deviceIpAddress=" + deviceIpAddress + ", registry=" + registry + ", direction=" + direction + "]";
    }
    
}
