package org.backend.smarthomebackend;

import SmartSystem.DbDevicesInterface;
import SmartSystem.SmartDevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SmartHomeController {
    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "ok");
    }

    @GetMapping("/devices")
    public Map<String, Object> getDevices(){
        ArrayList<String> test = new ArrayList<>();
        test = DbDevicesInterface.getAllDevicesList();
        return Map.of("devices", test);
    }
}
