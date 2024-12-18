package br.com.senior.service.message;

import java.util.concurrent.atomic.AtomicInteger;

public class CountNumberMessage {
    
    private static final int MAX_NUMBER = 200;
    
    private static final AtomicInteger messageNumber = new AtomicInteger(1);
    
    public static byte handlerMessageNumber(Byte number) {
        if (number == null)
            return getNextMessageNumber();
        else
            return number.byteValue();
    }
    
    public static byte getNextMessageNumber() {
        synchronized (messageNumber) {
            if (messageNumber.get() >= MAX_NUMBER) {
                messageNumber.set(1);
            }
            return (byte) messageNumber.getAndIncrement();
        }
    }
    
    public static byte getMessageNumber() {
        return (byte) messageNumber.get();
    }
    
}
