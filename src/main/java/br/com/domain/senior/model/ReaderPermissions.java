package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReaderPermissions {
    private final int readerID;
    
    private final Map<Integer, Collection<SimplePermission>> perm;
    
    public ReaderPermissions(int readerID) {
        this.readerID = readerID;
        this.perm = new HashMap<>();
    }
    
    public int getPermissionCount() {
        return this.perm.size();
    }
    
    public Iterator<Integer> getPermissionIterator() {
        Set<Integer> keySet = this.perm.keySet();
        return keySet.iterator();
    }
    
    public Collection<SimplePermission> getPermissionByCode(int code) {
        return this.perm.get(code);
    }
    
    public int getReaderID() {
        return this.readerID;
    }
    
    public void addPermission(int codprm, short inic, short fin) {
        Collection<SimplePermission> collection = this.perm.computeIfAbsent(codprm, k -> new ArrayList<>());
        collection.add(new SimplePermission(inic, fin));
    }
    
}
