package br.com.senior.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public DeviceNotFoundException() {
        super("Device not found.");
    }
    
    public DeviceNotFoundException(String message) {
        super(message);
    }
    
    public DeviceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DeviceNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
