package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.ModelTotalStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTotalStatisticRepository extends JpaRepository<ModelTotalStatistic, Long> {
    ModelTotalStatistic findByModelId(Long modelId);
}
