package br.com.domain.senior.model;

public class SimplePermission {
    private final short startTime;
    
    private final short endTime;
    
    public SimplePermission(short startTime, short endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public short getStartTime() {
        return this.startTime;
    }
    
    public short getEndTime() {
        return this.endTime;
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        int rangeResult = this.startTime / 60;
        int rangeResultMod = this.startTime % 60;
        result.append(String.format("%02d", rangeResult));
        result.append(":");
        result.append(String.format("%02d", rangeResultMod));
        result.append(" - ");
        rangeResult = this.endTime / 60;
        rangeResultMod = this.endTime % 60;
        result.append(String.format("%02d", rangeResult));
        result.append(":");
        result.append(String.format("%02d", rangeResultMod));
        return result.toString();
    }
    
}
