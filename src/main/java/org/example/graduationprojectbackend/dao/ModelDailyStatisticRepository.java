package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.ModelDailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ModelDailyStatisticRepository extends JpaRepository<ModelDailyStatistic, Long> {
    ModelDailyStatistic findByUsageDate(LocalDate usageDate);
    ModelDailyStatistic findByModelIdAndUsageDate(Long modelId, LocalDate usageDate);

    List<ModelDailyStatistic> findAll();

}
