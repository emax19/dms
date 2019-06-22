package com.dms.management.api;

import com.dms.management.model.DeviceLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface DeviceLogApi {

    @PostMapping("/device-log")
    boolean log(@RequestBody DeviceLog log) throws JsonProcessingException;

}
