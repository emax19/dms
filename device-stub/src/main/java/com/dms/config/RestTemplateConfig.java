package com.dms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OauthClientProperties oauthClientProperties) {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setClientId("dclient");
        resource.setClientSecret("asd");
        resource.setGrantType("client_credentials");
        resource.setAccessTokenUri("http://localhost:8080/oauth/token");

        DefaultAccessTokenRequest atr = new DefaultAccessTokenRequest();
        DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext(atr);
        return new OAuth2RestTemplate(resource, context);
    }

    @Bean
    public RestTemplate restTemplate(OAuth2RestTemplate oAuth2RestTemplate) {
        AtomicReference<OAuth2AccessToken> token = new AtomicReference<>(authorize(oAuth2RestTemplate));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(authorizationInterceptor(oAuth2RestTemplate, token));
        return restTemplate;
    }

    private ClientHttpRequestInterceptor authorizationInterceptor(OAuth2RestTemplate oAuth2RestTemplate, AtomicReference<OAuth2AccessToken> token) {
        return (request, body, execution) -> {
            if (Objects.isNull(token.get()) || Instant.now().isAfter(token.get().getExpiration().toInstant())) {
                token.set(oAuth2RestTemplate.getAccessToken());
            }
            request.getHeaders().add("Authorization", "Bearer " + token.get());
            return execution.execute(request, body);
        };
    }

    private OAuth2AccessToken authorize(OAuth2RestTemplate oAuth2RestTemplate) {
        try {
            return oAuth2RestTemplate.getAccessToken();
        } catch (Exception ex) {
            log.error("Cant authorize to device management service");
            throw ex;
        }
    }

}
