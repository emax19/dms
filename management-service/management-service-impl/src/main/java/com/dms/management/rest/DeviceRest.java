package com.dms.management.rest;

import com.dms.management.api.DeviceApi;
import com.dms.management.model.Device;
import com.dms.management.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeviceRest implements DeviceApi {

	private DeviceRepository deviceRepository;

	@Override
	public Device saveDevice(Device device) {
		return deviceRepository.save(device);
	}

	@Override
	public List<Device> findDevices(Pageable pageable) {
		return deviceRepository.findAll(pageable).getContent();
	}


}
