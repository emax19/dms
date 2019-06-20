package com.dms.device.oauth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Getter
public class DeviceClientDetails implements ClientDetails {

    @Setter
    private String clientId;
    @Setter
    private String clientSecret;

    private Integer accessTokenValiditySeconds = 3600;
    private Integer refreshTokenValiditySeconds = 3600;
    private boolean scoped = true;
    private boolean secretRequired = true;
    private Set<String> scope = Collections.singleton("log");
    private Set<String> resourceIds = Collections.singleton("device_listener");
    private Set<String> authorizedGrantTypes = Collections.singleton("client_credentials");
    private Collection<GrantedAuthority> authorities = Collections.singleton(() -> "device");
    private Set<String> registeredRedirectUri;
    private Map<String, Object> additionalInformation;

    public DeviceClientDetails(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }
}
