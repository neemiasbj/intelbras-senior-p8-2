package br.com.senior.service.devices;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricTechnology;
import br.com.domain.senior.model.CardReaderDevice;
import br.com.domain.senior.model.ManagerDevice;
import br.com.senior.exceptions.DeviceNotFoundException;
import br.com.senior.service.message.CountNumberMessage;
import br.com.senior.service.message.HeaderMessageFactory;
import br.com.senior.service.message.HeaderMessageFactoryImpl;
import br.com.senior.usecase.DecodeDeviceDataResponseUseCase;

@Component
public class DevicesController implements DeviceUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger("DevicesController");
    
    private final Collection<ManagerDevice> devices = new CopyOnWriteArrayList<>();
    
    public DevicesController() {
        DecodeDeviceDataResponseUseCase.addDeviceUpdateListener(this);
    }
    
    @Override
    public void onDeviceUpdate(Collection<ManagerDevice> updatedDevices) {
        logger.debug("Atualizando os dispositivos recebidos no DevicesController");
        for (ManagerDevice updatedDevice : updatedDevices) {
            updateOrAddDevice(updatedDevice);
        }
        logger.debug("|-> Total de dispositivos armazenados: {}", devices.size());
    }
    
    /**
     * Atualiza um dispositivo existente ou adiciona um novo à coleção.
     * 
     * @param newDevice O dispositivo a ser atualizado ou adicionado.
     */
    private void updateOrAddDevice(ManagerDevice newDevice) {
        devices.removeIf(device -> device.getId() == newDevice.getId() || device.getIp().equals(newDevice.getIp()));
        devices.add(newDevice);
    }
    
    public Collection<ManagerDevice> getAllDevices() {
        return Collections.unmodifiableCollection(devices);
    }
    
    /**
     * @param identifier Pode ser um Integer (ID) ou uma String (IP)
     * @return Optional contendo o ManagerDevice, caso encontrado.
     */
    public Optional<ManagerDevice> getManagerDevice(Object identifier) {
        return devices.stream().filter(device -> (identifier instanceof Integer && device.getId() == (int) identifier)
                                        || (identifier instanceof String && device.getIp().equals(identifier)))
                      .findFirst();
    }
    
    /**
     * @param searchTerm O valor de pesquisa (ID ou IP)
     * @return Lista de ManagerDevice que coincidem com o critério.
     */
    public ManagerDevice findDevicesByIdOrIp(Object searchTerm) {
        return devices.stream()
                      .filter(device -> (searchTerm instanceof Integer && device.getId() == (int) searchTerm)
                                                      || (searchTerm instanceof String && device.getIp().equals(searchTerm)))
                      .findFirst()
                      .orElseThrow(() -> new DeviceNotFoundException("Nenhum dispositivo encontrado para o termo de busca: " + searchTerm));
    }
    
    public Collection<BiometricTechnology> getBiometricTechnologies() {
        return Collections.emptyList();
    }
    
    public CardReaderDevice getCardReaderById(int cardReaderId) {
        return null;
    }
    
    public static HeaderMessage sendRequestDevice() {
        HeaderMessageFactory factory = HeaderMessageFactoryImpl.getInstance();
        // HeaderMessage deviceData =
        // factory.buildRequestDeviceData(CountNumberMessage.getNextMessageNumber());
        // LogUtils.logInfoMessageSendBytesToHexFormatt(deviceData.encode().array(),
        // logger);
        return factory.buildRequestDeviceData(CountNumberMessage.getNextMessageNumber());
    }
    
}
