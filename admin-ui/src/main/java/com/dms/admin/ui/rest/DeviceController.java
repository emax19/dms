package com.dms.admin.ui.rest;

import com.dms.management.client.ManagementServiceClient;
import com.dms.management.model.Action;
import com.dms.management.model.Device;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class DeviceController {

    private ManagementServiceClient managementServiceClient;

    @GetMapping({"/", "/device"})
    public String deviceById(Model model) {
        List<Device> devices = managementServiceClient.findDevices(1000, "name,asc");
        model.addAttribute(devices);
        return "device";
    }

    @GetMapping({"/device/{id}"})
    public String deviceById(@PathVariable String id, Model model) {
        Optional.ofNullable(managementServiceClient.findDeviceById(id)).ifPresent(model::addAttribute);
        model.addAttribute("deviceTypeList", managementServiceClient.findDeviceTypes(1000, "name,asc"));
        return "device-edit";
    }

    @PostMapping(value = "/device/save")
    public String saveDevice(@ModelAttribute Device device) {
        normalizeId(device);
        Device savedDevice = managementServiceClient.saveDevice(device);
        return "redirect:/device/" + savedDevice.getId();
    }

    @PostMapping(value = "/device/{id}/generate-token")
    public String addAction(@PathVariable String id) {
        Device device = new Device(id);
        device.setToken(UUID.randomUUID().toString());
        managementServiceClient.saveDevice(device);
        return "redirect:/device/" + id;
    }

    @PostMapping(value = "/device/{id}/action")
    public String addAction(@PathVariable String id, @ModelAttribute Action action) {
        managementServiceClient.addAction(id, action);
        return "redirect:/device/" + id;
    }

    @PostMapping(value = "/device/{id}/action/{method}/{name}/remove")
    public String removeAction(@PathVariable String id, @PathVariable String method, @PathVariable String name) {
        managementServiceClient.removeAction(id, method, name);
        return "redirect:/device/" + id;
    }

    private void normalizeId(@ModelAttribute Device device) {
        if (StringUtils.isEmpty(device.getId())) {
            device.setId(null);
        }
    }

}
