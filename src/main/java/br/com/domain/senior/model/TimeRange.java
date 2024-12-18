package br.com.domain.senior.model;

public class TimeRange {
    private final short[] timeRange;
    
    public TimeRange(short[] ranges) {
        if (ranges.length % 2 != 0) {
            throw new IllegalArgumentException("A quantidade de faixas horárias não pode ser ímpar. " + ranges.length);
        } else {
            for (int i = 0; i < ranges.length; ++i) {
                if (ranges[i] < 0 || ranges[i] > 1440) {
                    throw new IllegalArgumentException("A faixa horária " + i + " está inválida, o valor deve estar entre 0 e 1440. (" + ranges[i]
                                                       + ")");
                }
            }
            
            this.timeRange = ranges;
        }
    }
    
    public short[] getRanges() {
        return this.timeRange;
    }
    
    public String toString() {
        return getString(this.timeRange);
    }
    
    private static String getString(short[] subrange) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < subrange.length; ++i) {
            if (i % 2 == 0) {
                result.append("[");
            }
            
            int rangeResult = subrange[i] / 60;
            int rangeResultMod = subrange[i] % 60;
            result.append(String.format("%02d", rangeResult));
            result.append(":");
            result.append(String.format("%02d", rangeResultMod));
            if (i % 2 == 1) {
                result.append("]");
            }
            
            result.append(";");
        }
        
        return result.toString();
    }
    
}
