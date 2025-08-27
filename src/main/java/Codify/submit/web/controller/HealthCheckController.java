package Codify.submit.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HealthCheckController {
    @Value("${server.port}")
    private String port;

    @Value("${SERVER_ENV:unknown}")
    private String env;

    @GetMapping("/hc")
    public ResponseEntity<Map<String, String>> hc() {
        return ResponseEntity.ok(Map.of(
                "status","UP",
                "env",env,
                "port",port
        ));
    }

    @GetMapping("/env")
    public String getEnv() {
        return env;
    }
}
