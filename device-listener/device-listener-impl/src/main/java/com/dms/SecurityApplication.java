package com.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
		context.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach((t, m) -> System.out.println(t));
		context.getBeansOfType(ClientDetailsService.class).forEach((n, o) -> System.out.println(n + " | " + o));
	}

}
