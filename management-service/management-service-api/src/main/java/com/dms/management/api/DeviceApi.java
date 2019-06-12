package com.dms.management.api;


import com.dms.management.model.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DeviceApi {

	@GetMapping("/device")
	List<Device> findDevices(Pageable pageable);

	@PostMapping("/device")
	Device saveDevice(@RequestBody Device device);

}
