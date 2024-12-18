//package br.com.senior.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import br.com.domain.intelbras.model.enums.AccessTypeEnum;
//import br.com.domain.intelbras.model.enums.OnOffStatusEnum;
//import br.com.domain.intelbras.model.webhooks.WebhookAccessFastOnlineResponse;
//import br.com.domain.senior.abstration.HeaderMessage;
//import br.com.domain.senior.model.BiometricData;
//import br.com.domain.senior.model.CardReaderDevice;
//import br.com.domain.senior.model.ManagerDevice;
//import br.com.domain.senior.model.NotifyAccessEventBuilder;
//import br.com.domain.senior.model.Person;
//import br.com.domain.senior.model.enums.AccessType;
//import br.com.domain.senior.model.enums.DeviceCommType;
//import br.com.domain.senior.model.enums.DirectionsType;
//import br.com.domain.senior.model.enums.StatusType;
//import br.com.domain.senior.request.NotifyAccessEvent;
//import br.com.domain.senior.request.NotifyDeviceEvent;
//import br.com.domain.senior.request.RequestAccessValidation;
//import br.com.domain.senior.response.ACK;
//import br.com.domain.senior.response.AccessValidationResponse;
//import br.com.domain.senior.response.NACK;
//import br.com.senior.client.ClientCSMCommunication;
//import br.com.senior.exceptions.DeviceNotFoundException;
//import br.com.senior.service.devices.DevicesController;
//import br.com.senior.service.message.CountNumberMessage;
//import br.com.senior.service.message.HeaderMessageFactoryImpl;
//import br.com.util.ResponseUtil;
//import br.com.util.Utils;
//import br.com.util.UtilsProperties;
//
//@Component
//public class DriverControllerImpl implements DriverController {
//    
//    private static final Logger logger = LoggerFactory.getLogger("DriverController");
//    
//    private ManagerDevice device = null;
//    
//    private CardReaderDevice reader = null;
//    
//    private final UtilsProperties utilsProperties;
//    
//    private final DevicesController deviceController;
//    
//    private final ClientCSMCommunication clientCSMCommunication;
//    
//    private final Map<String, AccessValidationResponse> mapAwaitResposteNotify;
//    
//    public DriverControllerImpl(ClientCSMCommunication clientCSMCommunication, DevicesController deviceController, UtilsProperties utilsProperties) {
//        this.utilsProperties = utilsProperties;
//        this.deviceController = deviceController;
//        this.clientCSMCommunication = clientCSMCommunication;
//        this.mapAwaitResposteNotify = new HashMap<>();
//        ;
//        
//    }
//    
//    @Override
//    public ResponseEntity<Object> requestAccess(String deviceIpAddress, String registry, String direction) throws Exception {
//        logger.info("|<-- Requesting access for device IP: {}", deviceIpAddress);
//        
//        device = getManagerDeviceIP(deviceIpAddress);
//        
//        reader = device != null ? device.findDirectionAccess(direction) : null;
//        
//        WebhookAccessFastOnlineResponse responseAccess = new WebhookAccessFastOnlineResponse();
//        
//        if (device == null || reader == null) {
//            throw new DeviceNotFoundException("Device not found for IP: " + deviceIpAddress);
//        }
//        
//        HeaderMessage accessMessage = new RequestAccessValidation(CountNumberMessage.getNextMessageNumber(), reader.getId(), new Date(),
//                                        device.getGmtMinutes(), Boolean.FALSE, Long.valueOf(registry), null, null);
//        
//        logger.info("|<-- Byte message: {}", accessMessage.encode().array());
//        CompletableFuture<HeaderMessage> responseFuture = clientCSMCommunication.sendMessageWithResponse(accessMessage);
//        
//        try {
//            HeaderMessage response = responseFuture.get(utilsProperties.getTimeOutAwaitMessage(), TimeUnit.SECONDS);
//            responseAccess = new WebhookAccessFastOnlineResponse();
//            responseAccess.setAllowed(false);
//            responseAccess.setMessage("Access denied");
//            responseAccess.setMessageType(AccessTypeEnum.ACCESS_DENIED.toString());
//            if (response instanceof AccessValidationResponse accessValidationResponse) {
//                logger.info("|<-- {} for device: {}", accessValidationResponse.getSubReturnType().toString(), device.getIp());
//                responseAccess.setAllowed(accessValidationResponse.isAccessGranted());
//                responseAccess.setMessage(accessValidationResponse.getSubReturnType().getDescription());
//                responseAccess.setMessageType(AccessTypeEnum.fromValueString(String.valueOf(accessValidationResponse.getSubReturnType())));
//                mapAwaitResposteNotify.put(registry, accessValidationResponse);
//                
//                logger.info("|<-- Response: {}", responseAccess);
//                return ResponseUtil.resposeSuccess(responseAccess);
//            } else if (response instanceof NACK) {
//                logger.warn("Access denied for device: {}", device.getIp());
//                return ResponseUtil.resposeBadRequest(responseAccess);
//            } else {
//                logger.warn("Unexpected message type received: {}", response.getClass().getSimpleName());
//                return ResponseUtil.resposeBadRequest(responseAccess);
//            }
//            
//        } catch (Exception e) {
//            logger.error("Error processing access request for device: {}", device.getIp(), e);
//            throw e;
//        }
//        
//    }
//    
//    @Override
//    public ResponseEntity<Object> notifyAccessEvent(String registry,
//                                                    String deviceIpAddress,
//                                                    DirectionsType direction,
//                                                    String date,
//                                                    OnOffStatusEnum status,
//                                                    AccessTypeEnum accessType)
//                                    throws Exception {
//        logger.info("|<-- Notify Access Event: registry - {}, Ip - {}, Direction - {}, Date - {}, Status - {}, AccessType - {}", registry,
//                    deviceIpAddress, direction, date, status, accessType);
//        device = getManagerDeviceIP(deviceIpAddress);
//        
//        reader = device != null ? device.findDirectionAccess(direction.toString()) : null;
//        
//        if (device == null || reader == null) {
//            throw new DeviceNotFoundException("Device not found for IP: " + deviceIpAddress);
//        }
//        AccessValidationResponse accessMessage = mapAwaitResposteNotify.get(registry);
//        if (accessMessage != null) {
//            
//            NotifyAccessEventBuilder notifyBuilder = new NotifyAccessEventBuilder();
//            notifyBuilder.setDeviceID(reader.getId());
//            notifyBuilder.setEventDate(Utils.removeGmtToDateTimefromDate(date, (int) device.getGmtMinutes()));
//            notifyBuilder.setGmtOffset(device.getGmtMinutes());
//            notifyBuilder.setFunction((byte) 0);
//            notifyBuilder.setDirection(direction);
//            notifyBuilder.setGeneratedOnline(DeviceCommType.valueOf(status.toString()));
//            notifyBuilder.setCardID(Long.valueOf(registry));
//            notifyBuilder.setEventType(accessMessage.getSubReturnType().getId());
//            notifyBuilder.setLevelDest((byte) 0);
//            notifyBuilder.setAccessCreditRange((byte) 255);
//            notifyBuilder.setSmart(false);
//            notifyBuilder.setVehicleCard(0);
//            notifyBuilder.setLicensePlate(null);
//            notifyBuilder.setPersonId(null);
//            
//            HeaderMessage message = new NotifyAccessEvent(CountNumberMessage.getNextMessageNumber(), notifyBuilder);
//            
//            CompletableFuture<HeaderMessage> responseFuture = clientCSMCommunication.sendMessageWithResponse(message);
//            
//            try {
//                HeaderMessage response = responseFuture.get(utilsProperties.getTimeOutAwaitMessage(), TimeUnit.SECONDS);
//                if (response instanceof ACK ack) {
//                    logger.info("|<-- {} for device: {}", ack, device.getIp());
//                    return ResponseUtil.resposeSuccess(null);
//                } else if (response instanceof NACK) {
//                    logger.warn("Notification not received in CSM for device: {}", device.getIp());
//                    return ResponseUtil.resposeBadRequest(null);
//                } else {
//                    logger.warn("Notification not received in CSM for device: {}", response.getClass().getSimpleName());
//                    return ResponseUtil.resposeBadRequest(null);
//                }
//                
//            } catch (Exception e) {
//                logger.error("Error processing access request for device: {}", device.getIp(), e);
//                throw e;
//            }
//        }
//        return ResponseUtil.resposeSuccess(null);
//    }
//    
//    @Override
//    public ResponseEntity<Object> notifyPointAccessEvent(String registry,
//                                                         String deviceIpAddress,
//                                                         DirectionsType direction,
//                                                         String date,
//                                                         OnOffStatusEnum status,
//                                                         AccessTypeEnum accessType,
//                                                         String pis,
//                                                         String cpf,
//                                                         String responsible,
//                                                         String nsr)
//                                    throws Exception {
//        logger.info("|<-- Notify Point Access Event: registry - {}, Ip - {}, Direction - {}, Date - {}, Status - {}, AccessType - {}", registry,
//                    deviceIpAddress, direction, date, status, accessType);
//        device = getManagerDeviceIP(deviceIpAddress);
//        
//        reader = device != null ? device.findDirectionAccess(direction.toString()) : null;
//        
//        if (device == null || reader == null) {
//            throw new DeviceNotFoundException("Device not found for IP: " + deviceIpAddress);
//        }
//        // AccessValidationResponse accessMessage =
//        // mapAwaitResposteNotify.get(registry);
//        // if (accessMessage != null) {
//        
//        NotifyAccessEventBuilder notifyBuilder = new NotifyAccessEventBuilder();
//        notifyBuilder.setDeviceID(reader.getId());
//        notifyBuilder.setEventDate(Utils.removeGmtToDateTimefromDate(date, (int) device.getGmtMinutes()));
//        notifyBuilder.setGmtOffset(device.getGmtMinutes());
//        notifyBuilder.setFunction((byte) 0);
//        notifyBuilder.setDirection(direction);
//        notifyBuilder.setGeneratedOnline(DeviceCommType.valueOf(status.toString()));
//        // notifyBuilder.setCardID(Long.valueOf(registry));
//        notifyBuilder.setEventType(AccessType.ACCESS_GRANTED.getId());
//        notifyBuilder.setLevelDest((byte) 0);
//        notifyBuilder.setAccessCreditRange((byte) 255);
//        notifyBuilder.setSmart(false);
//        notifyBuilder.setVehicleCard(0);
//        notifyBuilder.setLicensePlate(null);
//        notifyBuilder.setPersonId(null);
//        notifyBuilder.setPis(Long.valueOf(pis));
//        notifyBuilder.setNsr(Integer.valueOf(nsr));
//        
//        HeaderMessage message = new NotifyAccessEvent(CountNumberMessage.getNextMessageNumber(), notifyBuilder);
//        
//        CompletableFuture<HeaderMessage> responseFuture = clientCSMCommunication.sendMessageWithResponse(message);
//        
//        try {
//            HeaderMessage response = responseFuture.get(utilsProperties.getTimeOutAwaitMessage(), TimeUnit.SECONDS);
//            if (response instanceof ACK ack) {
//                logger.info("|<-- {} for device: {}", ack, device.getIp());
//                return ResponseUtil.resposeSuccess(null);
//            } else if (response instanceof NACK) {
//                logger.warn("Notification not received in CSM for device: {}", device.getIp());
//                return ResponseUtil.resposeBadRequest(null);
//            } else {
//                logger.warn("Notification not received in CSM for device: {}", response.getClass().getSimpleName());
//                return ResponseUtil.resposeBadRequest(null);
//            }
//            
//        } catch (Exception e) {
//            logger.error("Error processing access request for device: {}", device.getIp(), e);
//            throw e;
//        }
//        // }
//        // return ResponseUtil.resposeSuccess(null);
//    }
//    
//    @Override
//    public void notifyDeviceStatus(String deviceIpAddress, DeviceCommType statusDevice, StatusType type, String date)
//                                    throws IOException, InterruptedException {
//        device = getManagerDeviceIP(deviceIpAddress);
//        
//        if (device != null) {
//            
//            logger.info("|<-- Notify Device Status -  ID: {} * Network: {} * Status: {}", Utils.padStart(String.valueOf(device.getId()), 10, '0'),
//                        Utils.formatIPAddress(device.getIp()), type.getDescription());
//            if (device.getStatus() != statusDevice) {
//                device.setStatus(statusDevice);
//                NotifyDeviceEvent message = new NotifyDeviceEvent(CountNumberMessage.getNextMessageNumber(), device.getId(), new Date(),
//                                                device.getGmtMinutes(), statusDevice, type.getId());
//                
//                logger.info("|<-- Sending message Notify Device Event -  Message: {} ", message.toString());
//                clientCSMCommunication.sendMessage(message);
//            }
//        } else {
//            logger.info("Device not found -  Network: {}", deviceIpAddress);
//        }
//        
//    }
//    
//    @Override
//    public void notifyDeviceStarted() {
//        // Lógica para notificar quando o dispositivo for iniciado
//    }
//    
//    @Override
//    public void notifyDeviceCommunicationStatus(StatusType newStatus) {
//        // Notifica o status de comunicação do dispositivo
//    }
//    
//    @Override
//    public void notifyDeviceTieBlocked(byte tie, boolean selected) {
//        // Lógica para notificar bloqueio
//    }
//    
//    @Override
//    public void notifyDeviceTieDefect(byte tie, boolean selected) {
//        // Lógica para notificar defeito
//    }
//    
//    @Override
//    public void notifyDeviceResourceStatus(byte resourceType, byte resourcePercentage) {
//        // Lógica para notificar status do recurso
//    }
//    
//    @Override
//    public void doTimeAndAttendance() {
//        // TODO document why this method is empty
//    }
//    
//    @Override
//    public void notifyAlarmed() {
//        
//        // buffer.getShort();
//        //
//        // int deviceID = buffer.getInt();
//        // Date eventDate = new Date(buffer.getInt() * 1000L);
//        // short gmt = buffer.getShort();
//        // DeviceCommType generatedOnline = DeviceCommType.fromValue(buffer.get());
//        // boolean active = buffer.get() == 0;
//        // return new NotifyDeviceAlarmed(msgNumber, deviceID, eventDate, gmt,
//        // generatedOnline, active);
//        
//    }
//    
//    @Override
//    public void requestBiometricData(String personId) {
//        logger.info("Requesting biometric data for person ID: {}", personId);
//        // Lógica para solicitar dados biométricos
//    }
//    
//    @Override
//    public void requestAllBiometricData() {
//        logger.info("Requesting all biometric data.");
//        // Lógica para solicitar todos os dados biométricos
//    }
//    
//    // @Override
//    // public void setPermissions(Collection<Permission> permissions) {
//    // this.permissions = new ArrayList<>(permissions);
//    // }
//    //
//    // @Override
//    // public Collection<Permission> getPermissions() {
//    // return new ArrayList<>(this.permissions);
//    // }
//    //
//    // @Override
//    // public Permission getPermissionById(short idPermission) {
//    // return this.permissions.stream().filter(perm -> perm.getIdPermission() ==
//    // idPermission).findFirst().orElse(null);
//    // }
//    //
//    // @Override
//    // public void addPermission(Permission permission) {
//    // this.permissions.add(permission);
//    // }
//    //
//    // @Override
//    // public void deletePermission(Permission permission) {
//    // this.permissions.remove(permission);
//    // }
//    
//    @Override
//    public Collection<ManagerDevice> getDevices() {
//        return new ArrayList<>(this.deviceController.getAllDevices());
//    }
//    
//    @Override
//    public ManagerDevice getManagerDevice(int deviceId) {
//        return this.deviceController.findDevicesByIdOrIp(deviceId);
//    }
//    
//    @Override
//    public ManagerDevice getManagerDeviceIP(String deviceId) {
//        return this.deviceController.findDevicesByIdOrIp(deviceId);
//    }
//    
//    @Override
//    public void insertUpdateBiometricData(Person person, BiometricData bioData) throws IOException, InterruptedException {
//        if (person == null || bioData == null) {
//            throw new NullPointerException("Person or Biometric Data cannot be null.");
//        }
//        
//        HeaderMessage updateBioMessage = HeaderMessageFactoryImpl.getInstance().buildInsertUpdateBiometricData(person, bioData);
//        clientCSMCommunication.sendMessage(updateBioMessage);
//    }
//    
//    @Override
//    public void sendRequestDateTime() throws IOException, InterruptedException {
//        HeaderMessage dateTimeRequest = HeaderMessageFactoryImpl.getInstance().buildRequestDateTime();
//        clientCSMCommunication.sendMessage(dateTimeRequest);
//    }
//    
//    @Override
//    public void sendMessage(HeaderMessage msg, Class<?> expectedResponseClass) throws IOException, InterruptedException {
//        clientCSMCommunication.sendMessage(msg);
//    }
//    
//}