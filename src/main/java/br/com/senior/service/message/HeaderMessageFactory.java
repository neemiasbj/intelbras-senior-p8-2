package br.com.senior.service.message;

import java.util.Date;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;

public interface HeaderMessageFactory {
    HeaderMessage buildRequestDeviceData(byte messageNumber);
    
    HeaderMessage buildSendAuthentication(byte[] certificado, short driver);
    
    HeaderMessage buildDeviceStatusResponse(byte var1, String[] var2);
    
    HeaderMessage buildNotifyDeviceTieBlocked(int var1, Date var2, short var3, DeviceCommType var4, byte var5, boolean var6);
    
    HeaderMessage buildNotifyDeviceTieDefect(int var1, Date var2, short var3, DeviceCommType var4, byte var5, boolean var6);
    
    HeaderMessage buildNotifyDeviceAlarmed(int var1, Date var2, short var3, DeviceCommType var4, boolean var5);
    
    HeaderMessage buildNotifyDeviceEvent(int var1, Date var2, short var3, DeviceCommType var4, byte var5);
    
    HeaderMessage buildNotifyDeviceResourceStatus(int var1, Date var2, short var3, DeviceCommType var4, byte var5, byte var6);
    
    HeaderMessage buildNotifyAccessEvent(int var1,
                                         Date var2,
                                         short var3,
                                         byte var4,
                                         DirectionsType var5,
                                         DeviceCommType var6,
                                         long var7,
                                         byte var9,
                                         byte var10,
                                         byte var11,
                                         int var12,
                                         long var13,
                                         boolean var15,
                                         long var16,
                                         String var18,
                                         String var19);
    
    HeaderMessage buildRequestAccessValidation(int var1, Date var2, short var3, boolean var4, long var5, String var7, String var8);
    
    HeaderMessage buildACK(byte var1);
    
    HeaderMessage buildRECV(byte var1);
    
    HeaderMessage buildNACK(byte var1, String var2, byte var3);
    
    HeaderMessage buildRequestDateTime();
    
    HeaderMessage buildListStatusResponse(byte var1, int var2, int var3, int var4, int var5, Date var6);
    
    HeaderMessage buildRequestBiometricData(String var1, byte var2);
    
    HeaderMessage buildDriverAlive(byte var1);
    
    HeaderMessage buildInsertUpdateBiometricData(Person var1, BiometricData var2);
    
    HeaderMessage buildInsertUpdateBiometricDataWithPis(long var1, BiometricData var3);
    
}
