package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.UserTotalStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserTotalStatisticRepository extends JpaRepository<UserTotalStatistic, Long> {

    // 根据 userId 查找所有的统计数据
    UserTotalStatistic findByUserId(Long userId);
    UserTotalStatistic save(UserTotalStatistic userTotalStatistic);

}