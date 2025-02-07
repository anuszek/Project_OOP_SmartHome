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

    @GetMapping("/users")
    public Map<String, Object> getUsers() {
        // fetch users list from database
        // return users list
        return Map.of("users", "users");
    }

    @PostMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> body) {
        // get user name from request body
        // delete specified user
        System.out.println(body);
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping("/user-admin")
    public ResponseEntity<String> userAdmin(@RequestBody Map<String, String> body) {
        // get user name from request body
        // make user admin
        System.out.println(body);
        return ResponseEntity.ok("User admin");
    }

    @GetMapping("/devices")
    public Map<String, Object> getDevices(){
        ArrayList<String> devices;
        devices = DbDevicesInterface.getAllDevicesList();
        return Map.of("devices", devices);
    }

    @PostMapping("/toggle-device")
    public ResponseEntity<String> toggleDevice(@RequestBody Map<String, String> body) {
        // get device from request body
        // change device state (online/offline)
        System.out.println(body);
        return ResponseEntity.ok("Device toggled");
    }

    @PostMapping("/update-device")
    public ResponseEntity<String> updateDevice(@RequestBody Map<String, String> body) {
        // get whole device from request body
        // match device with database
        // update properties
        System.out.println(body);
        return ResponseEntity.ok("Device updated");
    }

    @PostMapping("/add-device")
    public ResponseEntity<String> addDevice(@RequestBody Map<String, String> body) {
        // get device name from request body
        // add new device to database
        System.out.println(body);
        return ResponseEntity.ok("Device added");
    }

    @PostMapping("/delete-device")
    public ResponseEntity<String> deleteDevice(@RequestBody Map<String, String> body) {
        // get device id from request body
        // remove device from database
        System.out.println(body);
        return ResponseEntity.ok("Device deleted");
    }
}

