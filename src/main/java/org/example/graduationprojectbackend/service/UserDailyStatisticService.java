package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.dto.UserDailyUsageDto;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface UserDailyStatisticService {

    // 根据 userId 获取用户的统计数据
    List<UserDailyUsageDto> getStatisticsByUserId(Long userId);

    // 根据 userId 和日期获取特定的统计数据
    UserDailyStatistic getStatisticByUserIdAndUsageData(Long userId, LocalDate usageData);

    // 根据日期获取所有用户的统计数据
    List<UserDailyStatistic> getStatisticsByUsageData(LocalDate usageData);

    // 保存用户的统计数据
    UserDailyStatistic saveUserDailyStatistic(UserDailyStatistic userDailyStatistic);

    // 删除用户的统计数据
    void deleteUserDailyStatistic(Long id);

    UserDailyStatistic update(Long userId);
}
