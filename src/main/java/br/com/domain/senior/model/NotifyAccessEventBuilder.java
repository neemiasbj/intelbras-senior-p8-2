package br.com.domain.senior.model;

import java.util.Date;

import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;
import br.com.domain.senior.request.NotifyAccessEvent;

public class NotifyAccessEventBuilder {
    private byte messageNumber;
    
    private int deviceID;
    
    private Date eventDate;
    
    private short gmtOffset;
    
    private byte function;
    
    private DirectionsType direction;
    
    private DeviceCommType generatedOnline;
    
    private long cardID;
    
    private byte eventType;
    
    private byte levelDest;
    
    private byte accessCreditRange;
    
    private int nsr;
    
    private long pis;
    
    private boolean isSmart;
    
    private long vehicleCard;
    
    private String licensePlate;
    
    private String personId;
    
    public NotifyAccessEventBuilder setMessageNumber(byte messageNumber) {
        this.messageNumber = messageNumber;
        return this;
    }
    
    public NotifyAccessEventBuilder setDeviceID(int deviceID) {
        this.deviceID = deviceID;
        return this;
    }
    
    public NotifyAccessEventBuilder setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }
    
    public NotifyAccessEventBuilder setGmtOffset(short gmtOffset) {
        this.gmtOffset = gmtOffset;
        return this;
    }
    
    public NotifyAccessEventBuilder setFunction(byte function) {
        this.function = function;
        return this;
    }
    
    public NotifyAccessEventBuilder setDirection(DirectionsType direction) {
        this.direction = direction;
        return this;
    }
    
    public NotifyAccessEventBuilder setGeneratedOnline(DeviceCommType generatedOnline) {
        this.generatedOnline = generatedOnline;
        return this;
    }
    
    public NotifyAccessEventBuilder setCardID(long cardID) {
        this.cardID = cardID;
        return this;
    }
    
    public NotifyAccessEventBuilder setEventType(byte eventType) {
        this.eventType = eventType;
        return this;
    }
    
    public NotifyAccessEventBuilder setLevelDest(byte levelDest) {
        this.levelDest = levelDest;
        return this;
    }
    
    public NotifyAccessEventBuilder setAccessCreditRange(byte accessCreditRange) {
        this.accessCreditRange = accessCreditRange;
        return this;
    }
    
    public NotifyAccessEventBuilder setNsr(int nsr) {
        this.nsr = nsr;
        return this;
    }
    
    public NotifyAccessEventBuilder setPis(long pis) {
        this.pis = pis;
        return this;
    }
    
    public NotifyAccessEventBuilder setSmart(boolean isSmart) {
        this.isSmart = isSmart;
        return this;
    }
    
    public NotifyAccessEventBuilder setVehicleCard(long vehicleCard) {
        this.vehicleCard = vehicleCard;
        return this;
    }
    
    public NotifyAccessEventBuilder setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
    
    public NotifyAccessEventBuilder setPersonId(String personId) {
        this.personId = personId;
        return this;
    }
    
    public byte getMessageNumber() {
        return messageNumber;
    }
    
    public int getDeviceID() {
        return deviceID;
    }
    
    public Date getEventDate() {
        return eventDate;
    }
    
    public short getGmtOffset() {
        return gmtOffset;
    }
    
    public byte getFunction() {
        return function;
    }
    
    public DirectionsType getDirection() {
        return direction;
    }
    
    public DeviceCommType getGeneratedOnline() {
        return generatedOnline;
    }
    
    public long getCardID() {
        return cardID;
    }
    
    public byte getEventType() {
        return eventType;
    }
    
    public byte getLevelDest() {
        return levelDest;
    }
    
    public byte getAccessCreditRange() {
        return accessCreditRange;
    }
    
    public int getNsr() {
        return nsr;
    }
    
    public long getPis() {
        return pis;
    }
    
    public boolean isSmart() {
        return isSmart;
    }
    
    public long getVehicleCard() {
        return vehicleCard;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public String getPersonId() {
        return personId;
    }
    
    public NotifyAccessEvent build() {
        if (this.eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        }
        return new NotifyAccessEvent(messageNumber, deviceID, eventDate, gmtOffset, function, direction, generatedOnline, cardID, eventType,
                                        levelDest, accessCreditRange, nsr, pis, isSmart, vehicleCard, licensePlate, personId);
    }
    
}
