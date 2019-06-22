package com.dms.admin.ui.rest;

import com.dms.management.client.ManagementServiceClient;
import com.dms.management.model.Action;
import com.dms.management.model.Device;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class DeviceController {
    private static final RestTemplate restTemplate = new RestTemplate();
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

    @PostMapping(value = "/device/{id}/action/remove")
    public String removeAction(@PathVariable String id, @ModelAttribute Action action) {
        managementServiceClient.removeAction(id, action);
        return "redirect:/device/" + id;
    }

    @PostMapping(value = "/device/{id}/action-executor")
    @ResponseBody
    public ResponseEntity executeAction(@PathVariable String id, @ModelAttribute Action action) {
        Device device = managementServiceClient.findDeviceById(id);
        RequestEntity request = new RequestEntity<>(HttpMethod.valueOf(action.getMethod()), URI.create(device.getHost() + action.getRelativeUrl()));
        restTemplate.exchange(request, Object.class);
        return ResponseEntity.ok().build();
    }

    private void normalizeId(@ModelAttribute Device device) {
        if (StringUtils.isEmpty(device.getId())) {
            device.setId(null);
        }
    }

}
