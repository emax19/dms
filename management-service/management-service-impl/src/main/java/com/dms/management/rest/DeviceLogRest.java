package com.dms.management.rest;

import com.dms.management.api.DeviceLogApi;
import com.dms.management.model.Device;
import com.dms.management.model.DeviceLog;
import com.dms.management.repository.DeviceLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DeviceLogRest implements DeviceLogApi {

    private DeviceRest deviceRest;
    private DeviceLogRepository deviceLogRepository;

    @Override
    public boolean log(DeviceLog log) throws JsonProcessingException {
        Device device = new Device(log.getDeviceId());
        device.setLastActivity(log.getTimestamp());
        deviceRest.saveDevice(device);
        deviceLogRepository.save(log);
        return true;
    }

}
