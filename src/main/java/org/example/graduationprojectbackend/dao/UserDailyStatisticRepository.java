package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDailyStatisticRepository extends JpaRepository<UserDailyStatistic, Long> {

    // 根据 userId 查找用户的统计数据
    List<UserDailyStatistic> findByUserId(Long userId);

    // 根据 userId 和日期查找统计数据
    UserDailyStatistic findByUserIdAndUsageDate(Long userId, LocalDate usageData);

    // 根据日期查找统计数据
    List<UserDailyStatistic> findByUsageDate(LocalDate usageData);

    List<UserDailyStatistic> findAll();

}
