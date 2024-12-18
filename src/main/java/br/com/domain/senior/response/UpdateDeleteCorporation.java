package br.com.domain.senior.response;

import java.nio.ByteBuffer;
import java.util.List;

import br.com.domain.senior.abstration.HeaderMessage;

public class UpdateDeleteCorporation extends HeaderMessage {
    private byte operation;
    
    private List<Integer> devicesIds;
    
    private String corpName;
    
    private String corpCPF;
    
    private String corpCEI;
    
    private String corpCNPJ;
    
    public UpdateDeleteCorporation(byte msgNumber) {
        super(msgNumber, (byte) 5, (byte) 35);
    }
    
    public ByteBuffer encode() {
        return null;
    }
    
    public byte getOperation() {
        return this.operation;
    }
    
    public void setOperation(byte operation) {
        this.operation = operation;
    }
    
    public List<Integer> getDDevicesIds() {
        return this.devicesIds;
    }
    
    public void addDeviceId(int deviceId) {
        this.devicesIds.add(deviceId);
    }
    
    public List<Integer> getDevicesIds() {
        return this.devicesIds;
    }
    
    public void setDevicesIds(List<Integer> devicesIds) {
        this.devicesIds = devicesIds;
    }
    
    public String getCorpName() {
        return this.corpName;
    }
    
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
    
    public String getCorpCPF() {
        return this.corpCPF;
    }
    
    public void setCorpCPF(String corpCPF) {
        this.corpCPF = corpCPF;
    }
    
    public String getCorpCEI() {
        return this.corpCEI;
    }
    
    public void setCorpCEI(String corpCEI) {
        this.corpCEI = corpCEI;
    }
    
    public String getCorpCNPJ() {
        return this.corpCNPJ;
    }
    
    public void setCorpCNPJ(String corpCNPJ) {
        this.corpCNPJ = corpCNPJ;
    }
    
}
