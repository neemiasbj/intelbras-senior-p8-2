package br.com.domain.senior.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.Event;
import br.com.domain.senior.model.enums.DirectionsType;

public class CardReaderDevice extends Device implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private short previousLevel;
    
    private short nextLevel;
    
    private DirectionsType readerDirection;
    
    private boolean chkPermissionTimeZone;
    
    private boolean chkPersonTimeZone;
    
    private boolean chkPermission;
    
    private boolean chkRemoval;
    
    private boolean chkSituation;
    
    private boolean chkValidity;
    
    private boolean chkLevel;
    
    private boolean chkAntipassback;
    
    private boolean chkRestTime;
    
    private boolean chkLunchInterval;
    
    private boolean chkSad;
    
    private short antipassbackTime;
    
    private byte verifyAuthorizer;
    
    private byte verifyCredit;
    
    private byte maxNumberOfEscortedPeople;
    
    private byte vehicleControl;
    
    private byte readerType;
    
    private byte readerTechnology;
    
    private final List<Long> accompaniesWaiting = new ArrayList<>();
    
    private byte bioValidation;
    
    private BiometricTechnology bioTechnology;
    
    private byte bioSecurityLevel;
    
    public Collection<SimpleCard> getCardAllowedList() {
        return this.getManager().getCardAllowedList(this.getChildId());
    }
    
    public Collection<SimpleCard> getCardBlockedList() {
        return this.getManager().getCardBlockedList(this.getChildId());
    }
    
    public Collection<Date> getHolidays() {
        return this.getManager().getHolidays();
    }
    
    public void setNextLevel(short s) {
        this.nextLevel = s;
    }
    
    public short getNextLevel() {
        return this.nextLevel;
    }
    
    public ReaderPermissions getReaderPermissions() {
        return this.getManager().getReaderPermission(this.getId());
    }
    
    public void setPreviousLevel(short s) {
        this.previousLevel = s;
    }
    
    public short getPreviousLevel() {
        return this.previousLevel;
    }
    
    public void setDirection(DirectionsType direction) {
        this.readerDirection = direction;
    }
    
    public void setCheckFlags(short flags) {
        this.chkPermissionTimeZone = (flags & 1) == 1;
        this.chkPersonTimeZone = (flags >> 1 & 1) == 1;
        this.chkPermission = (flags >> 2 & 1) == 1;
        this.chkRemoval = (flags >> 3 & 1) == 1;
        this.chkSituation = (flags >> 4 & 1) == 1;
        this.chkValidity = (flags >> 5 & 1) == 1;
        this.chkLevel = (flags >> 6 & 1) == 1;
        this.chkAntipassback = (flags >> 7 & 1) == 1;
        this.chkRestTime = (flags >> 8 & 1) == 1;
        this.chkLunchInterval = (flags >> 9 & 1) == 1;
        this.chkSad = (flags >> 13 & 1) == 1;
    }
    
    public void setAntipassbackTime(short antipassbackTime) {
        this.antipassbackTime = antipassbackTime;
    }
    
    public boolean isChkPermissionTimeZOne() {
        return this.chkPermissionTimeZone;
    }
    
    public boolean isChkPersonTimeZone() {
        return this.chkPersonTimeZone;
    }
    
    public boolean isChkPermission() {
        return this.chkPermission;
    }
    
    public boolean isChkRemoval() {
        return this.chkRemoval;
    }
    
    public boolean isChkSituation() {
        return this.chkSituation;
    }
    
    public boolean isChkValidity() {
        return this.chkValidity;
    }
    
    public boolean isChkLevel() {
        return this.chkLevel;
    }
    
    public boolean isChkAntipassback() {
        return this.chkAntipassback;
    }
    
    public boolean isChkRestTime() {
        return this.chkRestTime;
    }
    
    public boolean isChkLunchInterval() {
        return this.chkLunchInterval;
    }
    
    public boolean isChkSad() {
        return this.chkSad;
    }
    
    public DirectionsType getReaderDirection() {
        return this.readerDirection;
    }
    
    public void setReaderType(byte b) {
        this.readerType = b;
    }
    
    public byte getReaderType() {
        return this.readerType;
    }
    
    public void setTechnology(byte b) {
        this.readerTechnology = b;
    }
    
    public void setVerifyAuthorizer(byte verifyAuthorizer) {
        if (verifyAuthorizer >= 0 && verifyAuthorizer <= 3) {
            this.verifyAuthorizer = verifyAuthorizer;
        } else {
            throw new IllegalArgumentException("Verifica autorizador deve ser um valor entre 0 e 3. " + verifyAuthorizer);
        }
    }
    
    public byte getVerifyAuthorizer() {
        return this.verifyAuthorizer;
    }
    
    public void setVerifyCredit(byte verifyCredit) {
        this.verifyCredit = verifyCredit;
    }
    
    public byte getVerifyCredit() {
        return this.verifyCredit;
    }
    
    public void setMaxNumerOfEscortedPeople(byte maxNumberOfEscortedPeople) {
        this.maxNumberOfEscortedPeople = maxNumberOfEscortedPeople;
    }
    
    public byte getMaxNumberOfEscortedPeople() {
        return this.maxNumberOfEscortedPeople;
    }
    
    public byte getReaderTechnology() {
        return this.readerTechnology;
    }
    
    public short getAntipassbackTime() {
        return this.antipassbackTime;
    }
    
    public void addEvent(Event event) {
        this.getParent().addEvent(event);
    }
    
    public Collection<Event> getEvents() {
        Collection<Event> eventos = new ArrayList<>();
        Collection<Event> todosEventos = this.getParent().getEvents();
        Iterator<Event> var3 = todosEventos.iterator();
        
        while (var3.hasNext()) {
            Event event = var3.next();
            if (event.getDevice() == this) {
                eventos.add(event);
            }
        }
        
        return eventos;
    }
    
    public void clearCardAllowedList() {
        this.getManager().clearCardAllowedList(this.getChildId());
    }
    
    public void clearCardBlockedList() {
        this.getManager().clearCardBlockedList(this.getChildId());
    }
    
    public boolean hasAccompaniesWaiting() {
        return !this.accompaniesWaiting.isEmpty();
    }
    
    public List<Long> getAccompaniesWaiting() {
        return Collections.unmodifiableList(this.accompaniesWaiting);
    }
    
    public void enqueueAccompany(long card) {
        this.accompaniesWaiting.add(card);
    }
    
    public void clearAccompaniesWaiting() {
        this.accompaniesWaiting.clear();
    }
    
    public void setBioValidation(byte bioValidation) {
        this.bioValidation = bioValidation;
    }
    
    public byte getBioValidation() {
        return this.bioValidation;
    }
    
    public void setBioTechnology(BiometricTechnology bioTech) {
        this.bioTechnology = bioTech;
    }
    
    public BiometricTechnology getBioTechnology() {
        return this.bioTechnology;
    }
    
    public void setBioSecurityLevel(byte securityLevel) {
        this.bioSecurityLevel = securityLevel;
    }
    
    public byte getBioSecurityLevel() {
        return this.bioSecurityLevel;
    }
    
    public byte getVehicleControl() {
        return this.vehicleControl;
    }
    
    public void setVehicleControl(byte vehicleControl) {
        this.vehicleControl = vehicleControl;
    }
    
}
