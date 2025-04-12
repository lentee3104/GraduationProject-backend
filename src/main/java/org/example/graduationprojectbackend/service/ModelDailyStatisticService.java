package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.ModelDailyStatistic;

import java.time.LocalDate;
import java.util.List;

public interface ModelDailyStatisticService {
    List<ModelDailyStatistic> findAll();
    ModelDailyStatistic save(ModelDailyStatistic modelDailyStatistic);
    ModelDailyStatistic update(Long modelId);
}
