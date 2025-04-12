package org.example.graduationprojectbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.graduationprojectbackend.dao.ModelDailyStatisticRepository;
import org.example.graduationprojectbackend.dao.ModelTotalStatisticRepository;
import org.example.graduationprojectbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UpdateAllStatisticController {

    UpdateAllStatisticService updateAllStatisticService;

    @Autowired
    public UpdateAllStatisticController(UpdateAllStatisticService updateAllStatisticService) {
        this.updateAllStatisticService = updateAllStatisticService;
    }


    @PostMapping("/updateAllStatistic")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> updateAllStatistic(String userId, String modelId) throws IOException {
        Long userIdNumber = Long.valueOf(userId);
        Long modelIdNumber = Long.valueOf(modelId);
        String response = updateAllStatisticService.updateAllStatistic(userIdNumber, modelIdNumber);
        // 判断返回的响应内容来决定 HTTP 状态码
        if (response.equals("success")) {
            // 如果更新成功，返回 200 OK 状态码和成功信息
            return ResponseEntity.ok("Statistics updated successfully.");
        } else {
            // 如果更新失败，返回 500 Internal Server Error 状态码和错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update statistics: " + response);
        }
    }
}
