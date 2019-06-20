package com.dms.device_listener.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/oauth")
public interface AuthServiceApi {

//	@PostMapping(value = "/token/refresh")
//	ResponseEntity<OAuth2AccessToken> refresh(@RequestBody RefreshTokenDto tokenDto) throws HttpRequestMethodNotSupportedException;


}
