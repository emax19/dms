package com.dms.management.client;

import com.dms.management.model.Action;
import com.dms.management.model.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("management-service")
public interface ManagementServiceClient {

    @GetMapping("/device")
    List<Device> findDevices(@RequestParam int size, @RequestParam(required = false) String sort);

    @GetMapping("/device/{id}")
    Device findDeviceById(@PathVariable String id);

    @PostMapping("/device")
    Device saveDevice(@RequestBody Device device);

    @GetMapping("/device/type")
    List<String> findDeviceTypes(@RequestParam int size, @RequestParam(required = false) String sort);

    @PostMapping("/device/{id}/action")
    void addAction(@PathVariable String id, @RequestBody Action action);

    @DeleteMapping("/device/{id}/action/{method}/{name}")
    void removeAction(@PathVariable String id, @PathVariable String method, @PathVariable String name);

}
