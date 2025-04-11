package org.example.graduationprojectbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.graduationprojectbackend.service.FlaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PredictionController {

    private final FlaskService flaskService;

    @Autowired
    public PredictionController(FlaskService flaskService) {
        this.flaskService = flaskService;
    }

    @PostMapping("/prediction")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> predict(@RequestParam("file") MultipartFile file, @RequestParam("params") String params) throws IOException {
        // 处理文件上传请求
        try {
            ResponseEntity<String> flaskResponse = flaskService.predict(file, params);
            return ResponseEntity.ok("Flask response: " + flaskResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error communicating with Flask: " + e.getMessage());
        }
    }
}
