package com.dms.rest;

import com.dms.config.StateProperties;
import com.dms.schedule.DeviceLogSchedule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class DeviceRest {

    private StateProperties stateProperties;
    private DeviceLogSchedule deviceLogSchedule;

    @PostMapping("/enable")
    public ResponseEntity enable() {
        deviceLogSchedule.getState().set(stateProperties.getStates().get("enabled"));
        log.info("device was enabled");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/disable")
    public ResponseEntity disable() {
        deviceLogSchedule.getState().set(stateProperties.getStates().get("disabled"));
        log.info("device was disabled");
        return ResponseEntity.ok().build();
    }

}
