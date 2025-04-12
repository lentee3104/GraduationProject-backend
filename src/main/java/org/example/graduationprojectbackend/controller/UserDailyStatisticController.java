package org.example.graduationprojectbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduationprojectbackend.dao.UserDailyStatisticRepository;
import org.example.graduationprojectbackend.dto.UserDailyUsageDto;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.example.graduationprojectbackend.service.UserDailyStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserDailyStatisticController {
    private final UserDailyStatisticService userDailyStatisticService;

    @Autowired
    public UserDailyStatisticController(UserDailyStatisticService userDailyStatisticService) {
        this.userDailyStatisticService = userDailyStatisticService;
    }

    @PostMapping("/getStatisticsByUserId")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserDailyUsageDto> getStatisticsByUserId(@RequestParam String userId) {
        Long number = Long.valueOf(userId);
        System.out.println("getStatisticsByUserId with userId is"+ userId);
        return userDailyStatisticService.getStatisticsByUserId(number);
    }
}
