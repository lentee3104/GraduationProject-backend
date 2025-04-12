package org.example.graduationprojectbackend.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyUsageDto {
    private Long id;              // ID
    private Long dailyCount;   // 每日使用次数
    private LocalDate usageDate;  // 使用日期
    private Long userId;          // 用户ID
    private String userName;      // 用户姓名
}
