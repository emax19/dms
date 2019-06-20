package com.dms.device.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeviceClientDetails implements ClientDetails {


	@Override
	public String getClientId() {
		return "dclient";
	}

	@Override
	public Set<String> getResourceIds() {
		return Collections.singleton("resource");
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return "asd";
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return Collections.singleton("read");
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return new HashSet<>(Arrays.asList("client_credentials"));
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> "some_role");
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return 60;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return 60;
	}

	@Override
	public boolean isAutoApprove(String s) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}
}
