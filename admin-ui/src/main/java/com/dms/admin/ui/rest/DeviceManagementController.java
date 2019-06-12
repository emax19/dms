package com.dms.admin.ui.rest;

import com.dms.management.client.DeviceClient;
import com.dms.management.model.Device;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class DeviceManagementController {

	private DeviceClient deviceClient;

	@GetMapping({"/"})
	public String deviceList(Model model) {
		List<Device> devices = deviceClient.findDevices(1000);
		model.addAttribute("devices", devices);
		return "device-management";
	}

}
