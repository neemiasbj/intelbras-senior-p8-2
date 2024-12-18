package br.com.domain.senior.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Permission implements Serializable {
    private static final long serialVersionUID = 485538348055012863L;
    
    private final short idPermission;
    
    private final Map<CardReaderDevice, ReaderTimeZone> readerTimeZones;
    
    public Permission(short idPermission) {
        if (idPermission >= 0 && idPermission <= 9999) {
            this.idPermission = idPermission;
            this.readerTimeZones = new HashMap<>();
        } else {
            throw new IllegalArgumentException("ID da permissão não pode ser menor que 0 nem maior que 10000. " + idPermission);
        }
    }
    
    public short getIdPermission() {
        return this.idPermission;
    }
    
    public Collection<ReaderTimeZone> getCardReaderTimeZones() {
        return Collections.unmodifiableCollection(this.readerTimeZones.values());
    }
    
    public boolean addCardReaderTimeZone(ReaderTimeZone cardReaderTimeZone) {
        if (cardReaderTimeZone != null) {
            this.readerTimeZones.put(cardReaderTimeZone.getCardReader(), cardReaderTimeZone);
            return true;
        } else {
            return false;
        }
    }
    
    public void clearCardReaderRanges() {
        this.readerTimeZones.clear();
    }
    
    public void addCardReaderTimeZones(Collection<ReaderTimeZone> ranges) {
        if (ranges != null) {
            Iterator<ReaderTimeZone> var2 = ranges.iterator();
            
            while (var2.hasNext()) {
                ReaderTimeZone readerTimeZone = var2.next();
                this.readerTimeZones.put(readerTimeZone.getCardReader(), readerTimeZone);
            }
        }
        
    }
    
    public boolean containsCardReader(CardReaderDevice cardReader) {
        return this.readerTimeZones.containsKey(cardReader);
    }
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else {
            Permission other = (Permission) obj;
            return this.idPermission == other.idPermission;
        }
    }
    
    public int hashCode() {
        return this.idPermission * 17;
    }
    
    public void removeCardReaderTimeZone(CardReaderDevice reader) {
        this.readerTimeZones.remove(reader);
    }
    
    public String toString() {
        return String.format("Permission(%d)", this.idPermission);
    }
    
}
