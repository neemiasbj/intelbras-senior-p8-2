package br.com.domain.senior.model;

import br.com.domain.senior.abstration.Device;

public class InputDevice extends Device {
    private boolean alarmed;
    
    public boolean isAlarmed() {
        return this.alarmed;
    }
    
    public void setAlarmed(boolean alarmed) {
        this.alarmed = alarmed;
    }
    
}
