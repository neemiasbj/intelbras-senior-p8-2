
package br.com.domain.intelbras.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.gson.Gson;

import br.com.domain.intelbras.model.api.DateTimeRequest;
import br.com.domain.intelbras.model.api.DateTimeRequest.Action;
import br.com.domain.senior.model.ManagerDevice;
import br.com.intelbras.utils.DigestAuthRestUtil;

public class IntelbrasService
//implements ISenior 
{
	private DigestAuthRestUtil digestAuthRestTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Gson gson = new Gson();
	private ManagerDevice device;
	private String deviceUri;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public IntelbrasService(ManagerDevice device) {
		this.device = device;
		deviceUri = String.format("http://%s", device.getIp());
		this.digestAuthRestTemplate = new DigestAuthRestUtil(device.getExtensibleData().get("username"),
				device.getExtensibleData().get("password"));
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
	}

//	@Override
	public Boolean handleDeviceStatusPendencies(ManagerDevice device) {
		try {
			DateTimeRequest dateTimeRequest = new DateTimeRequest();

			ResponseEntity<String> response = this.digestAuthRestTemplate.executeWithDigestAuth(
					dateTimeRequest.buildUrl(deviceUri, Action.GET_DATE_TIME), HttpMethod.GET, null);

			if (dateTimeRequest.isValidDate(response.getBody()))
				return true;
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

//	@Override
	public Boolean handleDeviceDateTimePendencies() {
		// TODO Auto-generated method stub
		return null;
	}
//
//	@Override
//	public Boolean handleResetDevicePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleBlockDevicePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleUnblockDevicePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleSetDeviceEmergencyPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleUnsetDeviceEmergencyPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleApolloIncludeCardPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleApolloExcludeCardPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleIncludeBiometryPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleExcludeBiometryPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleDevicePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleLoadHolidayListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleRemoveHolidayListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleActivateDeviceOutputPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleDeactivateDeviceOutputPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleDatamartUpdatedPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handlePersonLocationUpdatedPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleCollectEventPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleInputStatusPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleManufacturerUpdatedPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleUpdatePersonREPPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleLoadAllowCardListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleRemoveAllowCardListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleLoadBiometryListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleRemoveBiometryListPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleIncludeCardPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleExcludeCardPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleDeviceDisplayMessagePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleUpdateFirmwarePendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleIncludePhotoPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleExcludePhotoPendencies() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean handleLoadCredentialFacialList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}