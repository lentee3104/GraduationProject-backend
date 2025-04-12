package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.ModelTotalStatistic;

import java.util.List;

public interface ModelTotalStatisticService {
    List<ModelTotalStatistic> findAll();
    ModelTotalStatistic update(Long modelId);
}
