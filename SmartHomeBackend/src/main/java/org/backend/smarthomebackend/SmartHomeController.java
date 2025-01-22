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

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // process login credentials
        // example:
//        if ("admin".equals(username) && "password123".equals(password)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/login_data")
    public Map<String, Object> getLoginData(){
        return Map.of();
    }

    @GetMapping("/devices")
    public Map<String, Object> getDevices(){
        ArrayList<String> test = new ArrayList<>();
        test = DbDevicesInterface.getAllDevicesList();
        return Map.of("devices", test);
    }
}
