package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.UserDailyStatistic;

import java.util.List;
import java.util.Optional;

public interface UserDailyStatisticService {

    // 根据 userId 获取用户的统计数据
    List<UserDailyStatistic> getStatisticsByUserId(Long userId);

    // 根据 userId 和日期获取特定的统计数据
    Optional<UserDailyStatistic> getStatisticByUserIdAndDate(Long userId, String data);

    // 根据日期获取所有用户的统计数据
    List<UserDailyStatistic> getStatisticsByDate(String data);

    // 保存用户的统计数据
    UserDailyStatistic saveUserDailyStatistic(UserDailyStatistic userDailyStatistic);

    // 删除用户的统计数据
    void deleteUserDailyStatistic(Long id);
}
