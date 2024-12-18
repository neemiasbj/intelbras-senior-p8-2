package br.com.domain.senior.abstration;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.domain.senior.model.TimeRange;

public abstract class PermissionCollectionMessage extends HeaderMessage {
    private final Collection<PermissionCollectionMessage.Permission> permissions = new ArrayList<>();
    
    protected PermissionCollectionMessage(byte msgNumber, byte msgType, byte msgId) {
        super(msgNumber, msgType, msgId);
    }
    
    public final ByteBuffer encode() {
        return null;
    }
    
    public final PermissionCollectionMessage.Permission createAndAddPermission(short idPermission) {
        PermissionCollectionMessage.Permission permission = new PermissionCollectionMessage.Permission(idPermission);
        this.permissions.add(permission);
        return permission;
    }
    
    public final Collection<PermissionCollectionMessage.Permission> getPermissions() {
        return this.permissions;
    }
    
    public static final class ReaderTimeZone {
        private final TimeRange timeRange;
        
        private final int cardReaderCode;
        
        public ReaderTimeZone(TimeRange range, int cardReaderCode) {
            this.cardReaderCode = cardReaderCode;
            this.timeRange = range;
        }
        
        public int getCardReaderCode() {
            return this.cardReaderCode;
        }
        
        public TimeRange getTimeRange() {
            return this.timeRange;
        }
        
    }
    
    public static final class Permission {
        private final short idPermission;
        
        private final Map<Integer, PermissionCollectionMessage.ReaderTimeZone> readerTimeZones = new HashMap<>();
        
        public Permission(short idPermission) {
            this.idPermission = idPermission;
        }
        
        public boolean addCardReaderTimeZone(PermissionCollectionMessage.ReaderTimeZone rtz) {
            if (rtz != null) {
                this.readerTimeZones.put(rtz.getCardReaderCode(), rtz);
                return true;
            } else {
                return false;
            }
        }
        
        public short getIdPermission() {
            return this.idPermission;
        }
        
        public Collection<PermissionCollectionMessage.ReaderTimeZone> getReaderTimeZones() {
            return this.readerTimeZones.values();
        }
        
    }
    
}
