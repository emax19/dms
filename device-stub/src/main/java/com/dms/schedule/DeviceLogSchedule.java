package com.dms.schedule;

import com.dms.config.StateProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
//@Component
public class DeviceLogSchedule {

	@Getter
	private AtomicReference<Object> state;
	private RestTemplate restTemplate;

	public DeviceLogSchedule(StateProperties stateProperties, RestTemplate restTemplate) {
		this.state = new AtomicReference<>(stateProperties.getStates().get("enabled"));
		this.restTemplate = restTemplate;
	}

	@Scheduled(fixedRate = 5000)
	public void logState() {
//		restTemplate.postForEntity()
		log.info("The state was logged - " + state.get());
	}

}
