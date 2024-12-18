package br.com.domain.senior.request;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.NotifyAccessEventBuilder;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;

public class NotifyAccessEvent extends HeaderMessage {
    private final int deviceID;
    
    private final Date eventDate;
    
    private final short gmt;
    
    private final byte function;
    
    private final DirectionsType direction;
    
    private final DeviceCommType generatedOnline;
    
    private final long cardID;
    
    private final byte eventType;
    
    private final byte levelDest;
    
    private final byte accessCreditRange;
    
    private final int nsr;
    
    private final long pis;
    
    private final boolean isSmart;
    
    private final long vehicleCard;
    
    private final String licensePlate;
    
    private final String personId;
    
    public NotifyAccessEvent(byte messageNumber, int deviceID, Date eventDate, short gmtOffset, byte function, DirectionsType direction,
                             DeviceCommType generatedOnline, long cardID, byte eventType, byte levelDest, byte accessCreditRange, int nsr, long pis,
                             boolean isSmart, long vehicleCard, String licensePlate, String personId) {
        super(messageNumber, (byte) 2, (byte) 1);
        if (eventDate == null)
            throw new NullPointerException("Data do evento não pode ser nula. NotifyAccessEvent.");
        
        this.deviceID = deviceID;
        this.eventDate = new Date(eventDate.getTime());
        this.gmt = gmtOffset;
        this.function = function;
        this.direction = direction;
        this.generatedOnline = generatedOnline;
        this.cardID = cardID;
        this.eventType = eventType;
        this.levelDest = levelDest;
        this.accessCreditRange = accessCreditRange;
        this.nsr = nsr;
        this.pis = pis;
        this.isSmart = isSmart;
        this.vehicleCard = vehicleCard;
        this.licensePlate = licensePlate;
        this.personId = personId;
    }
    
    public NotifyAccessEvent(byte messageNumber, NotifyAccessEventBuilder event) {
        super(messageNumber, (byte) 2, (byte) 1);
        this.deviceID = event.getDeviceID();
        this.eventDate = event.getEventDate();
        this.gmt = event.getGmtOffset();
        this.function = event.getFunction();
        this.direction = event.getDirection();
        this.generatedOnline = event.getGeneratedOnline();
        this.cardID = event.getCardID();
        this.eventType = event.getEventType();
        this.levelDest = event.getLevelDest();
        this.accessCreditRange = event.getAccessCreditRange();
        this.nsr = event.getNsr();
        this.pis = event.getPis();
        this.isSmart = event.isSmart();
        this.vehicleCard = event.getVehicleCard();
        this.licensePlate = event.getLicensePlate();
        this.personId = event.getPersonId();
    }
    
    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(96);
        ByteBuffer header = super.getHeader();
        buf.put(header);
        buf.putInt(this.deviceID);
        buf.putInt((int) (this.eventDate.getTime() / 1000L));
        buf.putShort(this.gmt);
        buf.put(this.function);
        buf.put(this.direction.getId());
        buf.put(this.generatedOnline.getId());
        buf.putLong(this.cardID);
        buf.put(this.eventType);
        buf.put(this.levelDest);
        buf.put(this.accessCreditRange);
        if (this.isSmart) {
            buf.put((byte) 1);
        } else {
            buf.put((byte) 0);
        }
        
        buf.putLong(this.vehicleCard);
        buf.put(this.completeLicensePlate(this.licensePlate).getBytes());
        buf.put(this.completePersonId(this.personId).getBytes());
        buf.put(this.completeNsr(String.valueOf(this.nsr)).getBytes());
        buf.put(this.completePis(String.valueOf(this.pis)).getBytes());
        buf.putInt(0, buf.position());
        buf.flip();
        return buf;
    }
    
    private String completePis(String strPis) {
        if (strPis == null) {
            return "000000000000";
        } else if (strPis.length() < 12) {
            byte[] newPis = new byte[12];
            Arrays.fill(newPis, (byte) 48);
            System.arraycopy(strPis.getBytes(), 0, newPis, 12 - strPis.length(), strPis.length());
            return new String(newPis);
        } else {
            return strPis;
        }
    }
    
    private String completeNsr(String strNsr) {
        if (strNsr == null) {
            return "000000000";
        } else if (strNsr.length() < 9) {
            byte[] newNsr = new byte[9];
            Arrays.fill(newNsr, (byte) 48);
            System.arraycopy(strNsr.getBytes(), 0, newNsr, 9 - strNsr.length(), strNsr.length());
            return new String(newNsr);
        } else {
            return strNsr;
        }
    }
    
    private String completeLicensePlate(String strLicensePlate) {
        if (strLicensePlate == null) {
            return "          ";
        } else if (strLicensePlate.length() < 10) {
            byte[] newLicensePlate = new byte[10];
            Arrays.fill(newLicensePlate, (byte) 32);
            System.arraycopy(strLicensePlate.getBytes(), 0, newLicensePlate, 10 - strLicensePlate.length(), strLicensePlate.length());
            return new String(newLicensePlate);
        } else {
            return strLicensePlate;
        }
    }
    
    private String completePersonId(String strPersonId) {
        if (strPersonId == null) {
            return "                       ";
        } else if (strPersonId.length() < 23) {
            byte[] newPersonId = new byte[23];
            Arrays.fill(newPersonId, (byte) 32);
            System.arraycopy(strPersonId.getBytes(), 0, newPersonId, 23 - strPersonId.length(), strPersonId.length());
            return new String(newPersonId);
        } else {
            return strPersonId;
        }
    }
    
}
