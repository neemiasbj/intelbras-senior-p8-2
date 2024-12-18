package br.com.domain.senior.model;

import java.io.Serializable;

public final class BiometricTechnology implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final byte id;
    
    private short minSecurityLevel;
    
    private short maxSecurityLevel;
    
    public BiometricTechnology(byte id) {
        if (id < 0) {
            throw new IllegalArgumentException(String.format("A tecnologia biométrica não pode possuir um identificador negativo: %d", id));
        } else {
            this.id = id;
        }
    }
    
    public byte getId() {
        return this.id;
    }
    
    public short getMinSecurityLevel() {
        return this.minSecurityLevel;
    }
    
    public short getMaxSecurityLevel() {
        return this.maxSecurityLevel;
    }
    
    public void setMinSecurityLevel(short minSecurityLevel) {
        this.minSecurityLevel = minSecurityLevel;
    }
    
    public void setMaxSecurityLevel(short maxSecurityLevel) {
        this.maxSecurityLevel = maxSecurityLevel;
    }
    
    public String toString() {
        return String.format("Id: %d", this.getId());
    }
    
}
