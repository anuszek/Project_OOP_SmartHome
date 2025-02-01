package org.backend.smarthomebackend;


import SmartSystem.DbDevicesInterface;
import SmartSystem.SmartDevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

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

    @PostMapping("/toggle-device")
    public ResponseEntity<String> toggleDevice(@RequestBody Map<String, String> body) {
        // get device id from request body
        // handle the request
        // getDevices();
        System.out.println(body);
        Logger logger = Logger.getLogger(SmartHomeController.class.getName());
        logger.info("Toggling device with id: " + body.get("deviceId"));
        return ResponseEntity.ok("Device toggled");
    }
}

