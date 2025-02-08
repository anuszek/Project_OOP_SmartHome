package org.backend.smarthomebackend;


import SmartSystem.DbDevicesInterface;
import SmartSystem.DbUserLogin;
import SmartSystem.JsonUtil;
import SmartSystem.SmartDevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SmartHomeController {

    private static final Logger LOGGER = Logger.getLogger(SmartHomeController.class.getName());
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "ok");
    }

    @GetMapping("/users")
    public Map<String, Object> getUsers() {
        HashMap<String, Boolean> users = new HashMap<>();
        users = DbUserLogin.getAllUsersList();
        return Map.of("users", users);
    }

    @PostMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> body) {
        System.out.println(body);
        String username = body.get("userName");
        int response = DbUserLogin.deleteUser(username);
        if (response == 1) {
            return ResponseEntity.ok("User deleted");
        }else if (response == 0) {
            return ResponseEntity.badRequest().body("User not found");
        }else {
            return ResponseEntity.badRequest().body("Error occurred");
        }
    }

   @PostMapping("/user-admin")
   public ResponseEntity<String> userAdmin(@RequestBody Map<String, String> body) {
       String username = body.get("username");
       System.out.println(body);
       String response = DbUserLogin.grantAdmin(username);
       return ResponseEntity.ok(response);
   }

    @GetMapping("/devices")
    public Map<String, Object> getDevices(){
        ArrayList<String> devices;
        devices = DbDevicesInterface.getAllDevicesList();
        return Map.of("devices", devices);
    }

    @PostMapping("/toggle-device")
    public ResponseEntity<String> toggleDevice(@RequestBody Map<String, String> body) {
        String deviceId = body.get("deviceId");
        try {
            SmartDevice device = JsonUtil.deserialize(body.toString(), SmartDevice.class);
            device.toggle();
            DbDevicesInterface.updateDevice(deviceId, JsonUtil.serialize(device));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Logger logger = Logger.getLogger(SmartHomeController.class.getName());
        logger.info("Toggling device with id: " + body.get("deviceId"));
        return ResponseEntity.ok("Device toggled");
    }

    @PostMapping("/update-device")
    public ResponseEntity<String> updateDevice(@RequestBody Map<String, String> body) {
        String deviceId = body.get("deviceId");
        DbDevicesInterface.updateDevice(deviceId, body.toString());
        System.out.println(body);
        return ResponseEntity.ok("Device updated");
    }

    @PostMapping("/add-device")
    public ResponseEntity<String> addDevice(@RequestBody Map<String, String> body) {
        try {
            // Log properly formatted JSON
            String jsonString = OBJECT_MAPPER.writeValueAsString(body);
            LOGGER.info("Adding device with data: " + jsonString);

            // Ensure deviceType exists
            if (!body.containsKey("deviceType") || body.get("deviceType") == null) {
                LOGGER.severe("Missing or null deviceType in request.");
                return ResponseEntity.badRequest().body("Missing deviceType field.");
            }

            // Modify JSON (remove deviceType)
            String modifiedJson = JsonUtil.modifyJson(jsonString);

            // Get deviceType dynamically
            String deviceType = body.get("deviceType");
            Class<?> deviceClass = Class.forName(SmartSystem.SmartDevice.class.getPackageName() + "." + deviceType);

            // Ensure the class is a valid SmartDevice
            if (!SmartDevice.class.isAssignableFrom(deviceClass)) {
                LOGGER.severe("Provided class is not a valid SmartDevice: " + deviceType);
                return ResponseEntity.badRequest().body("Invalid device type.");
            }

            // Deserialize into a SmartDevice
            SmartDevice temp1 = (SmartDevice) JsonUtil.deserialize(modifiedJson, deviceClass);

            // Ensure the device has an ID
            if (temp1.getDeviceId() == null) {
                LOGGER.severe("Device ID is missing or invalid.");
                return ResponseEntity.badRequest().body("Invalid device ID.");
            }

            // Serialize and store the device
            String deviceData = JsonUtil.serialize(temp1);
            DbDevicesInterface.addDevice(temp1.getDeviceId(), deviceData);

            LOGGER.info("Device added successfully with ID: " + temp1.getDeviceId());
            return ResponseEntity.ok("Device added successfully.");

        } catch (ClassNotFoundException e) {
            LOGGER.severe("Invalid device type: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid device type.");
        } catch (Exception e) {
            LOGGER.severe("Error occurred while adding device: " + e.getMessage());
            return ResponseEntity.status(500).body("Error occurred while adding device.");
        }
    }

    @PostMapping("/delete-device")
    public ResponseEntity<String> deleteDevice(@RequestBody Map<String, String> body) {
        String deviceData = null;

            deviceData = body.toString();

        DbDevicesInterface.deleteDevice(deviceData);
        System.out.println(body);
        return ResponseEntity.ok("Device deleted");
    }
}

