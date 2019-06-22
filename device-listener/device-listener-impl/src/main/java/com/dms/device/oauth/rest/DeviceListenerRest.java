package com.dms.device.oauth.rest;

import com.dms.management.client.ManagementServiceClient;
import com.dms.management.model.DeviceLog;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DeviceListenerRest {

    private ManagementServiceClient managementServiceClient;

    @PostMapping("/device-log")
    private ResponseEntity log(@AuthenticationPrincipal OAuth2Authentication principal, @RequestBody Map<String, Object> state) {
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());
        deviceLog.setDeviceId(principal.getOAuth2Request().getClientId());
        deviceLog.setState(state);
        managementServiceClient.log(deviceLog);
        return ResponseEntity.ok().build();
    }

}
