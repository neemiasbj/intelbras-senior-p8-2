package br.com.senior.usecase;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.DeviceCollectionMessage;
import br.com.domain.senior.model.BiometricTechnology;
import br.com.domain.senior.model.CardReaderDevice;
import br.com.domain.senior.model.InputDevice;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.ModuleController;
import br.com.domain.senior.model.OutputDevice;
import br.com.domain.senior.model.enums.DirectionsType;
import br.com.domain.senior.response.DeviceDataResponse;
import br.com.domain.senior.response.UpdateDeleteDevice;
import br.com.senior.service.devices.DeviceUpdateListener;
import br.com.util.Utils;

public class DecodeDeviceDataResponseUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger("DecodeDeviceDataResponseUseCase");
    
    private static final int MANAGER_DEVICE = 1;
    
    private static final int CONTROLLER_MODULE = 2;
    
    private static final int CARD_READER = 3;
    
    private static final int INPUT_DEVICE = 4;
    
    private static final int OUTPUT_DEVICE = 5;
    
    private static final List<DeviceUpdateListener> listeners = new ArrayList<>();
    
    public static void addDeviceUpdateListener(DeviceUpdateListener listener) {
        listeners.add(listener);
    }
    
    private static void notifyDeviceUpdate(Collection<ManagerDevice> devices) {
        for (DeviceUpdateListener listener : listeners) {
            listener.onDeviceUpdate(devices);
        }
    }
    
    public static DeviceDataResponse decodeDeviceDataResponse(byte messageNumber, byte messageType, byte messageId, ByteBuffer buffer) {
        DeviceDataResponse deviceDataResponse = new DeviceDataResponse(messageNumber, messageType, messageId);
        decode(deviceDataResponse, buffer);
        return deviceDataResponse;
    }
    
    private static void decode(DeviceCollectionMessage deviceCollectionMessage, ByteBuffer buffer) {
        if (buffer.remaining() < 2) {
            throw new IllegalArgumentException("Buffer insuficiente para ler o cabeçalho da resposta do dispositivo.");
        }
        
        buffer.getShort();
        byte bioCount = buffer.get();
        
        for (int i = 0; i < bioCount; ++i) {
            if (buffer.remaining() < 5) {
                throw new IllegalArgumentException("Buffer insuficiente para ler os dados da tecnologia biométrica.");
            }
            byte id = buffer.get();
            BiometricTechnology bioTech = new BiometricTechnology(id);
            bioTech.setMaxSecurityLevel(buffer.getShort());
            bioTech.setMinSecurityLevel(buffer.getShort());
            deviceCollectionMessage.addBioTechnology(bioTech);
        }
        
        populateDevices(deviceCollectionMessage, buffer);
    }
    
    public static void populateDevices(DeviceCollectionMessage deviceCollectionMessage, ByteBuffer buffer) {
        if (buffer.remaining() < 2) {
            throw new IllegalArgumentException("Buffer insuficiente para ler a quantidade de dispositivos.");
        }
        
        int devCount = buffer.getShort();
        
        for (int i = 0; i < devCount; ++i) {
            if (buffer.remaining() < 9) {
                throw new IllegalArgumentException("Buffer insuficiente para ler os dados do dispositivo.");
            }
            
            int id = buffer.getInt();
            int parentId = buffer.getInt();
            String description = Utils.getDescription(buffer);
            byte category = buffer.get();
            
            Device device = createDevice(category);
            device.setId(id);
            device.setDescription(description);
            populateExtensibleCount(device, buffer);
            
            switch (category) {
                case MANAGER_DEVICE:
                    ManagerDevice managerDevice = (ManagerDevice) device;
                    managerDevice.setIp(buffer.getInt());
                    setManagerDeviceDetails(buffer, managerDevice);
                    logger.debug("MANAGER_DEVICE - {}", managerDevice.toString());
                    break;
                    
                case CONTROLLER_MODULE:
                    device.setChildId(buffer.getInt());
                    break;
                    
                case CARD_READER:
                    CardReaderDevice cardReader = (CardReaderDevice) device;
                    // logger.info("CARD_READER - {}", cardReader.toString());
                    populateCardReaderDevice(buffer, deviceCollectionMessage, cardReader);
                    break;
                    
                case INPUT_DEVICE:
                    device.setChildId(buffer.getInt());
                    buffer.get();
                    buffer.getInt();
                    break;
                    
                case OUTPUT_DEVICE:
                    device.setChildId(buffer.getInt());
                    buffer.get();
                    break;
                
                default:
                    logger.warn("Categoria desconhecida de dispositivo: " + category);
            }
            deviceCollectionMessage.addDevice(device, parentId);
            Collection<ManagerDevice> updatedDevices = deviceCollectionMessage.getDevices();
            notifyDeviceUpdate(updatedDevices);
        }
    }
    
    private static void populateCardReaderDevice(ByteBuffer buffer, DeviceCollectionMessage deviceCollectionMessage, CardReaderDevice cardReader) {
        cardReader.setChildId(buffer.get());
        cardReader.setPreviousLevel(buffer.getShort());
        cardReader.setNextLevel(buffer.getShort());
        
        byte msgSize = buffer.get();
        DirectionsType direction = DirectionsType.valueOf(msgSize);
        cardReader.setDirection(direction != null ? direction : DirectionsType.BOTH);
        
        cardReader.setCheckFlags(buffer.getShort());
        cardReader.setVerifyAuthorizer(buffer.get());
        cardReader.setMaxNumerOfEscortedPeople(buffer.get());
        cardReader.setReaderType(buffer.get());
        cardReader.setTechnology(buffer.get());
        cardReader.setAntipassbackTime(buffer.getShort());
        cardReader.setBioValidation(buffer.get());
        
        byte bioTechId = buffer.get();
        BiometricTechnology bioTech = deviceCollectionMessage.findBioTechnologyById(bioTechId);
        cardReader.setBioTechnology(bioTech);
        cardReader.setBioSecurityLevel(buffer.get());
        cardReader.setVerifyCredit(buffer.get());
        
        buffer.get();
        buffer.getInt();
        buffer.getInt();
        
        int authorizedPeopleCount = buffer.getInt();
        for (int j = 0; j < authorizedPeopleCount; ++j) {
            buffer.get();
            buffer.get();
        }
        
        cardReader.setVehicleControl(buffer.get());
    }
    
    public static Device createDevice(byte categoria) {
        // logger.info("Create Device - Byte: {}", categoria);
        switch (categoria) {
            case MANAGER_DEVICE:
                return new ManagerDevice();
                
            case CONTROLLER_MODULE:
                return new ModuleController();
                
            case CARD_READER:
                return new CardReaderDevice();
                
            case INPUT_DEVICE:
                return new InputDevice();
                
            case OUTPUT_DEVICE:
                return new OutputDevice();
                
            default:
                logger.info("Não foi encontrado um Tipo de Device compatível. Byte: {}", categoria);
                return null;
        }
    }
    
    private static void populateExtensibleCount(Device device, ByteBuffer buf) {
        
        byte extensibleDataCount = buf.get();
        for (byte j = 0; j < extensibleDataCount; ++j) {
            byte idSize = buf.get();
            String identification = Utils.getString(buf, idSize);
            short valueLength = buf.getShort();
            String value = Utils.getString(buf, valueLength);
            device.addExtensibleData(identification, value);
        }
    }
    
    private static void setManagerDeviceDetails(ByteBuffer buffer, ManagerDevice managerDevice) {
        String cnpj = Utils.getDescription(buffer);
        String cpf = Utils.getDescription(buffer);
        if (!cnpj.isEmpty()) {
            managerDevice.setNationalNumber(Long.parseLong(cnpj));
        }
        if (!cpf.isEmpty()) {
            managerDevice.setNationalNumber(Long.parseLong(cpf));
        }
        
        String cei = Utils.getDescription(buffer);
        if (!cei.isEmpty()) {
            managerDevice.setCEINumber(Long.parseLong(cei));
        }
        
        managerDevice.setCorporationName(Utils.getDescription(buffer));
        managerDevice.setLocale(Utils.getDescription(buffer));
        String factorationNumber = Utils.getDescription(buffer);
        if (!factorationNumber.isEmpty()) {
            managerDevice.setFactorationNumber(Long.parseLong(factorationNumber));
        }
        
        int length = buffer.get();
        buffer.get(new byte[length]);
        managerDevice.setGmtMinutes(buffer.getShort());
        
        buffer.get();
        managerDevice.setDstStart(buffer.getLong());
        managerDevice.setDstEnd(buffer.getLong());
        
        buffer.getInt();
        byte cardMasklength = buffer.get();
        
        byte[] cardMask = new byte[cardMasklength];
        buffer.get(cardMask);
        
        buffer.getInt();
        buffer.get();
        short accessmessagecount = buffer.getShort();
        int j = 0;
        
        while (true) {
            if (j >= accessmessagecount) {
                break;
            }
            if (buffer.remaining() < 3) {
                logger.error("Buffer insuficiente para ler os eventos de acesso.");
                return;
            }
            buffer.getShort();
            byte accessmessagelength = buffer.get();
            if (buffer.remaining() < accessmessagelength) {
                logger.error("Buffer insuficiente para ler a mensagem de acesso.");
                return;
            }
            byte[] accessdefaultmessage = new byte[accessmessagelength];
            buffer.get(accessdefaultmessage);
            if (buffer.remaining() < 2) {
                logger.error("Buffer insuficiente para ler dados adicionais de acesso.");
                return;
            }
            buffer.get();
            buffer.get();
            j++;
        }
        
    }
    
    public static UpdateDeleteDevice decodeUpdateDeleteDevice(byte messageNumber, byte messageType, byte messageId, ByteBuffer buffer) {
        UpdateDeleteDevice deviceConfiguration = new UpdateDeleteDevice(messageNumber, messageType, messageId);
        updateDeleteDevice(deviceConfiguration, buffer);
        return deviceConfiguration;
    }
    
    public static void updateDeleteDevice(UpdateDeleteDevice deviceConfiguration, ByteBuffer buffer) {
        buffer.getShort();
        byte operation = buffer.get();
        if (operation != 0 && operation != 1) {
            if (operation == 2) {
                setDeletedDevices(deviceConfiguration, buffer);
            }
        } else {
            DecodeDeviceDataResponseUseCase.populateDevices(deviceConfiguration, buffer);
        }
        
        deviceConfiguration.setOperation(operation);
    }
    
    private static void setDeletedDevices(UpdateDeleteDevice deviceConfiguration, ByteBuffer buffer) {
        int devCount = buffer.getShort();
        logger.trace(devCount + " dispositivos para deletar...");
        
        for (int i = 0; i < devCount; ++i) {
            deviceConfiguration.addDeletedDeviceId(buffer.getInt());
        }
        
    }
    
}
