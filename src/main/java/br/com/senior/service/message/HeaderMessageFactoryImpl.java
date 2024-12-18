package br.com.senior.service.message;

import java.security.InvalidParameterException;
import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.NotifyAccessEventBuilder;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;
import br.com.domain.senior.request.NotifyAccessEvent;
import br.com.domain.senior.request.NotifyDeviceAlarmed;
import br.com.domain.senior.request.NotifyDeviceEvent;
import br.com.domain.senior.request.NotifyDeviceResourceStatus;
import br.com.domain.senior.request.NotifyDeviceTieBlocked;
import br.com.domain.senior.request.NotifyDeviceTieDefect;
import br.com.domain.senior.request.RequestAccessValidation;
import br.com.domain.senior.request.RequestBiometricData;
import br.com.domain.senior.request.RequestDateTimeMessage;
import br.com.domain.senior.request.RequestDeviceData;
import br.com.domain.senior.request.SendAuthentication;
import br.com.domain.senior.response.ACK;
import br.com.domain.senior.response.CollectEventsResponse;
import br.com.domain.senior.response.DeviceStatusResponse;
import br.com.domain.senior.response.DriverAlive;
import br.com.domain.senior.response.InsertUpdateBiometricData;
import br.com.domain.senior.response.InsertUpdateBiometricDataWithPis;
import br.com.domain.senior.response.ListStatusResponse;
import br.com.domain.senior.response.NACK;
import br.com.domain.senior.response.RECV;

public class HeaderMessageFactoryImpl implements HeaderMessageFactory {
    private static HeaderMessageFactory instance;
    
    private HeaderMessageFactoryImpl() {
    }
    
    public HeaderMessage buildRequestDeviceData(byte messageNumber) {
        return new RequestDeviceData(messageNumber);
    }
    
    public HeaderMessage buildSendAuthentication(byte[] certificado, short driver) {
        if (certificado == null) {
            throw new NullPointerException("Certificado não pode ser nulo. MessageFactoryImpl");
        } else {
            return new SendAuthentication(certificado, driver);
        }
    }
    
    public HeaderMessage buildDeviceStatusResponse(byte messageNumber, String[] properties) {
        if (properties == null) {
            throw new NullPointerException("Proprieadedes não podem ser nulas. MessageFactoryImpl");
        } else {
            return new DeviceStatusResponse(messageNumber, properties);
        }
    }
    
    public HeaderMessage buildCollectEvents(byte messageNumber, int deviceID, int acessEventsCount, int alarmeEventsCount) {
        return new CollectEventsResponse(messageNumber, deviceID, acessEventsCount, alarmeEventsCount);
    }
    
    public HeaderMessage buildNotifyDeviceTieBlocked(int deviceID, Date eventDate, short gmt, DeviceCommType geradaOnline, byte laco, boolean ativo) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyDeviceTieBlocked(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmt, geradaOnline, laco, ativo);
        }
    }
    
    public HeaderMessage buildNotifyDeviceTieDefect(int deviceID, Date eventDate, short gmt, DeviceCommType geradaOnline, byte laco, boolean active) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl");
        } else {
            return new NotifyDeviceTieDefect(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmt, geradaOnline, laco, active);
        }
    }
    
    public HeaderMessage buildNotifyDeviceAlarmed(int deviceID, Date eventDate, short gmt, DeviceCommType online, boolean active) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyDeviceAlarmed(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmt, online, active);
        }
    }
    
    public HeaderMessage buildNotifyDeviceEvent(int deviceID, Date eventDate, short gmt, DeviceCommType generatedOnline, byte eventType) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyDeviceEvent(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmt, generatedOnline, eventType);
        }
    }
    
    public HeaderMessage buildNotifyDeviceResourceStatus(int deviceID,
                                                         Date eventDate,
                                                         short gmt,
                                                         DeviceCommType generatedOnline,
                                                         byte resourceType,
                                                         byte resourceLeft) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyDeviceResourceStatus(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmt, generatedOnline, resourceType,
                                            resourceLeft);
        }
    }
    
    public HeaderMessage buildNotifyAccessEvent(int deviceID,
                                                Date eventDate,
                                                short gmtOffset,
                                                byte function,
                                                DirectionsType direction,
                                                DeviceCommType geradaOnline,
                                                long cardID,
                                                byte eventType,
                                                byte levelDest,
                                                byte accessCreditRange,
                                                int nsr,
                                                long pis,
                                                boolean isSmart,
                                                long vehicleCard,
                                                String licensePlate,
                                                String personId) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyAccessEvent(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmtOffset, function, direction, geradaOnline,
                                            cardID, eventType, levelDest, accessCreditRange, nsr, pis, isSmart, vehicleCard, licensePlate, personId);
        }
    }
    
    public HeaderMessage buildNotifyAccessEventModel(NotifyAccessEventBuilder event) {
        if (event == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new NotifyAccessEvent(CountNumberMessage.getNextMessageNumber(), event);
        }
    }
    
    public HeaderMessage buildRequestAccessValidation(int deviceID,
                                                      Date eventDate,
                                                      short gmtOffset,
                                                      boolean holiday,
                                                      long cardID,
                                                      String personId,
                                                      String licensePlate) {
        if (eventDate == null) {
            throw new NullPointerException("Data não pode ser nula. MessageFactoryImpl.");
        } else {
            return new RequestAccessValidation(CountNumberMessage.getNextMessageNumber(), deviceID, eventDate, gmtOffset, holiday, cardID, personId,
                                            licensePlate);
        }
    }
    
    public HeaderMessage buildACK(byte messageNumber) {
        return new ACK(messageNumber);
    }
    
    public HeaderMessage buildRequestDateTime() {
        return new RequestDateTimeMessage(CountNumberMessage.getNextMessageNumber());
    }
    
    public HeaderMessage buildListStatusResponse(byte messageNumber,
                                                 int deviceID,
                                                 int recordCount,
                                                 int usedBytes,
                                                 int availableBytes,
                                                 Date lastUpdate) {
        if (lastUpdate == null) {
            throw new NullPointerException("Data da ultima atualização não pode ser nula. MessageFactoryImpl.");
        } else {
            return new ListStatusResponse(messageNumber, deviceID, recordCount, usedBytes, availableBytes, lastUpdate);
        }
    }
    
    public HeaderMessage buildInsertUpdateBiometricData(Person person, BiometricData bioData) {
        if (person == null) {
            throw new NullPointerException("A pessoa não pode ser nula. MessageFactoryImpl.");
        } else if (bioData == null) {
            throw new NullPointerException("O dado biométrico alterado não pode ser nulo. MessageFactoryImpl.");
        } else {
            return new InsertUpdateBiometricData(CountNumberMessage.getNextMessageNumber(), person, bioData);
        }
    }
    
    public HeaderMessage buildInsertUpdateBiometricDataWithPis(long pis, BiometricData bioData) {
        if (pis == 0L) {
            throw new InvalidParameterException("O PIS não pode ser zero. MessageFactoryImpl.");
        } else if (bioData == null) {
            throw new NullPointerException("O dado biométrico não pode ser nulo. MessageFactoryImpl.");
        } else {
            return new InsertUpdateBiometricDataWithPis(CountNumberMessage.getNextMessageNumber(), pis, bioData);
        }
    }
    
    public HeaderMessage buildRequestBiometricData(String personId, byte technologyId) {
        return new RequestBiometricData(CountNumberMessage.getNextMessageNumber(), personId, technologyId);
    }
    
    public HeaderMessage buildDriverAlive(byte messageNumber) {
        return new DriverAlive(messageNumber);
    }
    
    public static synchronized HeaderMessageFactory getInstance() {
        if (instance == null) {
            instance = new HeaderMessageFactoryImpl();
        }
        return instance;
    }
    
    public HeaderMessage buildNACK(byte messsageNumber, String errorMessage, byte errorCause) {
        if (errorMessage == null) {
            throw new NullPointerException("Mensagem de erro nao pode ser nula.");
        } else {
            return new NACK(messsageNumber, errorMessage, errorCause);
        }
    }
    
    public HeaderMessage buildRECV(byte messageNumber) {
        return new RECV(messageNumber);
    }
    
}
