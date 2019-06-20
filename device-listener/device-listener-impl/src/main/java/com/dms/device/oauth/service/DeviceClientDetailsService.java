package com.dms.device.oauth.service;

import com.dms.device.oauth.model.DeviceClientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class DeviceClientDetailsService implements ClientDetailsService {

	@Override
	public DeviceClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return new DeviceClientDetails();
	}
}

