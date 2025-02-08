package org.backend.smarthomebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import SmartSystem.DbUserLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    private DbUserLogin dbUserLogin;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }

        this.dbUserLogin = new DbUserLogin();
        // needs checking if there are any users in the database (i think)
//        if () {
//            return ResponseEntity.status(500).body(Map.of("error", "Internal server error"));
//        }

        ArrayList<Boolean> results = dbUserLogin.checkUser(username, password);
        Map<String, Object> response = new HashMap<>();

        if (results.get(0)) {
            if (results.get(1)) { // is admin
                response.put("authenticated", true);
                response.put("isAdmin", true);
                response.put("userData", dbUserLogin.returnUserData(username));
                return ResponseEntity.ok(response);
            } else {
                response.put("authenticated", true);
                response.put("isAdmin", false);
                response.put("userData", dbUserLogin.returnUserData(username));
                return ResponseEntity.ok(response);
            }
        } else {
            response.put("authenticated", false);
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }
   }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> credentials) {
        this.dbUserLogin = new DbUserLogin();

        String username = credentials.get("username");
        String password = credentials.get("password");
        Logger logger = LoggerFactory.getLogger(LoginController.class);
        logger.info("Register request received for username: {}", username);
        String result = dbUserLogin.register(username, password);
        logger.info("Register result for username {}: {}", username, result);

        Map<String, Object> response = new HashMap<>();
        if(result.equals("User already exists")) {
            response.put("authenticated", false);
        } else {
            response.put("authenticated", true);
        }
        response.put("isAdmin", false);
        response.put("userData", dbUserLogin.returnUserData(username));

        return ResponseEntity.ok(response);
    }
}