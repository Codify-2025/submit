package Codify.submit.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/submit")
public class HealthCheckController {
    @Value("${server.port}")
    private String port;

    @Value("${SERVER_ENV:unknown}")
    private String env;

    @GetMapping("/test/hc")
    public ResponseEntity<Map<String, String>> hc() {
        return ResponseEntity.ok(Map.of(
                "status","UP",
                "env",env,
                "port",port,
                "name", "김유진3"
        ));
    }

    @GetMapping("/env")
    public String getEnv() {
        return env;
    }
}
