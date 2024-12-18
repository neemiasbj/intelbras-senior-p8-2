package br.com.domain.senior.model;

import br.com.domain.senior.abstration.Device;

public class OutputDevice extends Device {
    private boolean enabled;
    
    public void setStatus(boolean enable) {
        this.enabled = enable;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
}
