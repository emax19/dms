package com.dms.device.oauth.service;

import com.dms.device.oauth.model.DeviceClientDetails;
import com.dms.management.client.ManagementServiceClient;
import com.dms.management.model.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class DeviceClientDetailsService implements ClientDetailsService {

    private ManagementServiceClient managementServiceClient;

    @Override
    public DeviceClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Device device = managementServiceClient.findDeviceById(clientId);
        return new DeviceClientDetails(device.getId(), device.getToken());
    }

}

