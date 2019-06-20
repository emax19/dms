package com.dms.management.api;


import com.dms.management.model.Action;
import com.dms.management.model.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface DeviceApi {

    @GetMapping("/device")
    List<Device> findDevices(Pageable pageable);

    @GetMapping("/device/type")
    List<String> findDeviceTypes(Pageable pageable);

    @GetMapping("/device/{id}")
    Device findDeviceById(@PathVariable String id);

    @PostMapping("/device")
    Device saveDevice(@RequestBody Device device);

    @PostMapping("/device/{id}/action")
    void addAction(@PathVariable String id, @RequestBody Action action);

    @DeleteMapping("/device/{id}/action/{name}/{method}")
    void removeAction(@PathVariable String id, @PathVariable String name, @PathVariable String method);

}
