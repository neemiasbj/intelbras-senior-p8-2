package br.com.domain.senior.model;

import java.io.Serializable;

public final class ReaderTimeZone implements Serializable {
    private static final long serialVersionUID = -4539227270738041426L;
    
    private final transient TimeRange timeRange;
    
    private final transient CardReaderDevice cardReader;
    
    public ReaderTimeZone(TimeRange range, CardReaderDevice cardReader) {
        if (cardReader == null) {
            throw new IllegalArgumentException("Leitora da faixa de leitora não pode ser nula.");
        } else if (range == null) {
            throw new IllegalArgumentException("Faixa de leitora não pode ser nula.");
        } else {
            this.timeRange = range;
            this.cardReader = cardReader;
        }
    }
    
    public CardReaderDevice getCardReader() {
        return this.cardReader;
    }
    
    public TimeRange getTimeRange() {
        return this.timeRange;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
            ReaderTimeZone other = (ReaderTimeZone) obj;
            if (this.cardReader == null) {
                if (other.cardReader != null) {
                    return false;
                }
            } else if (!this.cardReader.equals(other.cardReader)) {
                return false;
            }
            
            if (this.timeRange == null) {
                if (other.timeRange != null) {
                    return false;
                }
            } else if (this.timeRange != other.timeRange) {
                return false;
            }
            
            return true;
        }
    }
    
    public int hashCode() {
        int prime = 13;
        prime = prime * this.cardReader.getId();
        prime *= this.timeRange.hashCode();
        return prime;
    }
    
    public String toString() {
        return String.format("ReaderTimeZone(range=%s; reader=%s)", this.timeRange, this.cardReader);
    }
    
}
