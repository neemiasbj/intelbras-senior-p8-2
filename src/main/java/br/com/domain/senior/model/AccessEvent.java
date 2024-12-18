package br.com.domain.senior.model;

import java.util.Date;

import br.com.domain.senior.abstration.Event;
import br.com.domain.senior.model.enums.AccessType;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;

public class AccessEvent extends Event {
    private final DirectionsType directionAccess;
    
    private final long card;
    
    private AccessType type;
    
    private final byte levelDest;
    
    private final byte accessCreditRange;
    
    private final int nsr;
    
    private final long pis;
    
    private final long vehicleCard;
    
    private final String licensePlate;
    
    private final String personId;
    
    public AccessEvent(CardReaderDevice readerDev, long card, AccessType type, DirectionsType directionAccess, DeviceCommType currentStatus,
                       Date eventDate, short gmt, byte levelDest, byte accessCreditRange, int nsr, long pis, long vehicleCard, String licensePlate,
                       String personId) {
        if (readerDev != null && eventDate != null && type != null) {
            this.setDevice(readerDev);
            this.setGmt(gmt);
            this.card = card;
            this.type = type;
            this.directionAccess = directionAccess;
            this.setStatusComm(currentStatus);
            this.setDate(eventDate);
            this.levelDest = levelDest;
            this.accessCreditRange = accessCreditRange;
            this.nsr = nsr;
            this.pis = pis;
            this.vehicleCard = vehicleCard;
            this.licensePlate = licensePlate;
            this.personId = personId;
        } else {
            throw new NullPointerException("Leitora, data do evetno e o tipo do acesso não podem ser nulos. AccessEvent.");
        }
    }
    
    public AccessEvent(CardReaderDevice readerDev, long card, AccessType type, DirectionsType directionAccess, DeviceCommType currentStatus,
                       Date eventDate, short gmt, byte levelDest, byte accessCreditRange) {
        this(readerDev, card, type, directionAccess, currentStatus, eventDate, gmt, levelDest, accessCreditRange, 0, 0L, 0L, "          ",
             "                       ");
    }
    
    public AccessEvent(CardReaderDevice readerDev, Date eventDate, short gmt, int nsr, long pis) {
        this(readerDev, 0L, AccessType.ACCESS_GRANTED, DirectionsType.EXIT, DeviceCommType.OFFLINE, eventDate, gmt, (byte) 99, (byte) -1, nsr, pis,
             0L, "          ", "                       ");
    }
    
    public DirectionsType isDirectionEntrance() {
        return this.directionAccess;
    }
    
    public long getCard() {
        return this.card;
    }
    
    public CardReaderDevice getDevice() {
        return (CardReaderDevice) super.getDevice();
    }
    
    public AccessType getType() {
        return this.type;
    }
    
    public String toString() {
        return String.format("%s com o cartão %s", this.getType(), this.getCard());
    }
    
    public void setAccessType(AccessType newType) {
        if (newType == null) {
            throw new NullPointerException("Tipo do acesso não pode ser nulo. AccessEvent.");
        } else {
            this.type = newType;
        }
    }
    
    public byte getLevelDest() {
        return this.levelDest;
    }
    
    public byte getAccessCreditRange() {
        return this.accessCreditRange;
    }
    
    public int getNsr() {
        return this.nsr;
    }
    
    public long getPis() {
        return this.pis;
    }
    
    public long getVehicleCard() {
        return this.vehicleCard;
    }
    
    public String getLicensePlate() {
        return this.licensePlate;
    }
    
    public String getPersonId() {
        return this.personId;
    }
    
}
