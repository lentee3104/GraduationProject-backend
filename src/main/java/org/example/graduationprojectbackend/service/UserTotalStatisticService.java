package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.UserTotalStatistic;

public interface UserTotalStatisticService {

    // 根据 userId 获取用户的总统计数据
    UserTotalStatistic getTotalStatisticByUserId(Long userId);

    // 保存用户的总统计数据
    UserTotalStatistic saveUserTotalStatistic(UserTotalStatistic userTotalStatistic);

    // 删除用户的总统计数据
    void deleteUserTotalStatistic(Long id);

    UserTotalStatistic update(Long userId);
}
