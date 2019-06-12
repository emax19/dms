package com.dms.management.client;

import com.dms.management.model.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("management-service")
public interface DeviceClient {

	@GetMapping("/management/device")
	List<Device> findDevices(@RequestParam int size);

	@PostMapping("/management/device")
	Device saveDevice(@RequestBody Device device);

}
