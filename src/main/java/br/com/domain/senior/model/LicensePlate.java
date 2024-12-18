package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.List;

public class LicensePlate {
    private final String licensePlate;
    
    private final int readers;
    
    private final byte validation;
    
    private List<LicensePlateCard> listConductorCard = new ArrayList<>();
    
    private List<LicensePlateCard> listVehicleCard = new ArrayList<>();
    
    public LicensePlate(String licensePlate, int readers, byte validation, List<LicensePlateCard> listConductorCard,
                        List<LicensePlateCard> listVehicleCard) {
        this.licensePlate = licensePlate;
        this.readers = readers;
        this.validation = validation;
        this.listConductorCard = listConductorCard;
        this.listVehicleCard = listVehicleCard;
    }
    
    public String getLicensePlate() {
        return this.licensePlate;
    }
    
    public int getReaders() {
        return this.readers;
    }
    
    public byte getValidation() {
        return this.validation;
    }
    
    public List<LicensePlateCard> getListConductorCard() {
        return this.listConductorCard;
    }
    
    public List<LicensePlateCard> getListVehicleCard() {
        return this.listVehicleCard;
    }
    
    public String toString() {
        return String.format("LicensePlateDatagram[ Placa = %s , Validação = %d , Qtde Crachá Condutor: %d, Qtde Crachá Veículo: %d]",
                             this.licensePlate, this.validation, this.listConductorCard.size(), this.listVehicleCard.size());
    }
    
}
