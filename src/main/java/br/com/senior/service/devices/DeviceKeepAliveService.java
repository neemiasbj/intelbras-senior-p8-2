package br.com.senior.service.devices;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.StatusType;
import br.com.domain.senior.request.NotifyDeviceEvent;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.message.CountNumberMessage;
import br.com.util.Utils;
import br.com.util.UtilsProperties;
import jakarta.annotation.PostConstruct;

@Service
public class DeviceKeepAliveService {

	private static final Logger logger = LoggerFactory.getLogger("KeepAlive");

	private final IntelbrasService IntelbrasService;

	@Autowired
	private DevicesController devicesController;

	private final ClientCSMCommunication csmCommunication;

	private final UtilsProperties utilsProperties;

	private static final ScheduledExecutorService schedulerExecutorKeepAliveDevice = Executors
			.newSingleThreadScheduledExecutor(r -> new Thread(r, "DeviceKeepAliveService"));

	public DeviceKeepAliveService(ClientCSMCommunication csmCommunication, IntelbrasService IntelbrasService,
			UtilsProperties utilsProperties) {
		this.IntelbrasService = IntelbrasService;
		this.csmCommunication = csmCommunication;
		this.utilsProperties = utilsProperties;
	}

	@PostConstruct
	public void init() {
		schedulerExecutorKeepAliveDevice.scheduleAtFixedRate(() -> {
			try {
				keepAliveDevice();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}, 0, utilsProperties.getKeepAlive(), TimeUnit.SECONDS);
	}

	private void keepAliveDevice() throws IOException, InterruptedException {
		Collection<ManagerDevice> devices = devicesController.getAllDevices();

		if (csmCommunication.isRunning()) {
			logger.info("|--- Start Keep Alive ...");

			for (ManagerDevice device : devices) {
				try {
					Boolean isAlive = IntelbrasService.handleDeviceStatusPendencies(device);

					if (isAlive)
						handleOnlineDevice(device);
					else
						handleOfflineDevice(device);
				} catch (Exception e) {
					handleOfflineDevice(device);
				}
			}
			logger.info("|--- End Keep Alive ...");
		} else {
			logger.info("|--- Keep Alive waiting for socket connection to be reestablished ...");
		}
	}

	private void handleOnlineDevice(ManagerDevice device) throws IOException, InterruptedException {
		logger.info("|<-- Notify Device Status -  ID: {} * Network: {} * Status: {}",
				Utils.padStart(String.valueOf(device.getId()), 10, '0'), Utils.formatIPAddress(device.getIp()),
				StatusType.DEVICE_ONLINE.getDescription());
		HeaderMessage message = new NotifyDeviceEvent(CountNumberMessage.getNextMessageNumber(), device.getId(),
				new Date(), device.getGmtMinutes(), DeviceCommType.ONLINE, StatusType.DEVICE_ONLINE.getId());
		device.setStatus(DeviceCommType.ONLINE);
		csmCommunication.sendMessage(message);
	}

	private void handleOfflineDevice(ManagerDevice device) throws IOException, InterruptedException {
		final NotifyDeviceEvent message = new NotifyDeviceEvent(CountNumberMessage.getNextMessageNumber(),
				device.getId(), new Date(), device.getGmtMinutes(), DeviceCommType.OFFLINE,
				StatusType.DEVICE_OFFLINE.getId());
		logger.info("|<-- Notify Device Status -  ID: {} * Network: {} * Status: {}",
				Utils.padStart(String.valueOf(device.getId()), 10, '0'), Utils.formatIPAddress(device.getIp()),
				StatusType.DEVICE_OFFLINE.getDescription());
		device.setStatus(DeviceCommType.OFFLINE);
		csmCommunication.sendMessage(message);
	}

}
