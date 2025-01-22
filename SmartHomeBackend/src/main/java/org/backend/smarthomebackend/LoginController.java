package org.backend.smarthomebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import SmartSystem.DbUserLogin;

import java.util.ArrayList;
import java.util.HashMap;


public class LoginController {
    private DbUserLogin dbUserLogin;

    @PostMapping("/login")
    public HashMap<String, Boolean> login(@RequestBody String username, @RequestBody String password) {
        ArrayList<Boolean> results = dbUserLogin.checkUser(username, password);
        HashMap<String,Boolean> response = new HashMap<>();
        if (results.get(0)) {
            if (results.get(1)) {
                response.put(dbUserLogin.returnUserData(username), true);
                return response;
            } else {
                response.put(dbUserLogin.returnUserData(username), false);
                return response;
            }
        } else {
            response.put("Invalid username or password",false);
            return response;
        }
   }

    @PostMapping("/register")
    public String register(@RequestBody String username, @RequestBody String password) {
        return dbUserLogin.register(username, password);
    }
}