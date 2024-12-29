package org.backend.smarthomebackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SmartHomeController {
    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "ok");
    }
}
