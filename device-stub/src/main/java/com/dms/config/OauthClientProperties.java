package com.dms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth-client")
public class OauthClientProperties {

    private String clientId;
    private String secret;
    private String grantType;
    private String accessTokenEndpoint;
    private String logEndpoint;

}
