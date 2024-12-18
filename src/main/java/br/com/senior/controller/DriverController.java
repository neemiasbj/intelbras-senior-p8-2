//package br.com.senior.controller;
//
//import java.io.IOException;
//import java.util.Collection;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import br.com.domain.intelbras.model.enums.AccessTypeEnum;
//import br.com.domain.intelbras.model.enums.OnOffStatusEnum;
//import br.com.domain.senior.abstration.HeaderMessage;
//import br.com.domain.senior.model.BiometricData;
//import br.com.domain.senior.model.ManagerDevice;
//import br.com.domain.senior.model.Person;
//import br.com.domain.senior.model.enums.DeviceCommType;
//import br.com.domain.senior.model.enums.DirectionsType;
//import br.com.domain.senior.model.enums.StatusType;
//
//@Component
//public interface DriverController {
//    
//    ResponseEntity<Object> requestAccess(String deviceIpAddress, String registry, String direction) throws Exception;
//    
//    void notifyDeviceStatus(String deviceIpAddress, DeviceCommType statusDevice, StatusType type, String date)
//                                    throws IOException, InterruptedException;
//    
//    ResponseEntity<Object> notifyAccessEvent(String registry,
//                                             String deviceIpAddress,
//                                             DirectionsType direction,
//                                             String date,
//                                             OnOffStatusEnum status,
//                                             AccessTypeEnum accessType)
//                                    throws Exception;
//    
//    ResponseEntity<Object> notifyPointAccessEvent(String registry,
//                                                  String deviceIpAddress,
//                                                  DirectionsType direction,
//                                                  String date,
//                                                  OnOffStatusEnum status,
//                                                  AccessTypeEnum accessType,
//                                                  String pis,
//                                                  String cpf,
//                                                  String responsible,
//                                                  String nsr)
//                                    throws Exception;
//    
//    void notifyDeviceStarted();
//    
//    void notifyDeviceCommunicationStatus(StatusType newStatus);
//    
//    void notifyDeviceTieBlocked(byte tie, boolean selected);
//    
//    void notifyDeviceTieDefect(byte tie, boolean selected);
//    
//    void notifyDeviceResourceStatus(byte resourceType, byte resourcePercentage);
//    
//    void doTimeAndAttendance();
//    
//    void notifyAlarmed();
//    
//    void requestBiometricData(String personId);
//    
//    void requestAllBiometricData();
//    
//    // void setPermissions(Collection<Permission> permissions);
//    //
//    // Collection<Permission> getPermissions();
//    //
//    // Permission getPermissionById(short idPermission);
//    //
//    // void addPermission(Permission permission);
//    //
//    // void deletePermission(Permission permission);
//    
//    Collection<ManagerDevice> getDevices();
//    
//    ManagerDevice getManagerDevice(int deviceId);
//    
//    ManagerDevice getManagerDeviceIP(String deviceId);
//    
//    void insertUpdateBiometricData(Person person, BiometricData bioData) throws IOException, InterruptedException;
//    
//    void sendRequestDateTime() throws IOException, InterruptedException;
//    
//    void sendMessage(HeaderMessage msg, Class<?> expectedResponseClass) throws IOException, InterruptedException;
//    
//}