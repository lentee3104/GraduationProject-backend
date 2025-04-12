package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.ModelTotalStatisticRepository;
import org.example.graduationprojectbackend.entity.ModelTotalStatistic;
import org.example.graduationprojectbackend.service.ModelTotalStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModelTotalStatisticServiceImpl implements ModelTotalStatisticService {

    private final ModelTotalStatisticRepository modelTotalStatisticRepository;

    @Autowired
    public ModelTotalStatisticServiceImpl(ModelTotalStatisticRepository modelTotalStatisticRepository) {
        this.modelTotalStatisticRepository = modelTotalStatisticRepository;
    }


    @Override
    public List<ModelTotalStatistic> findAll() {
        return modelTotalStatisticRepository.findAll();
    }

    @Override
    public ModelTotalStatistic update(Long modelId) {
        ModelTotalStatistic modelTotalStatistic = modelTotalStatisticRepository.findByModelId(modelId);
        if (modelTotalStatistic == null) {
            modelTotalStatistic = new ModelTotalStatistic();
            modelTotalStatistic.setModelId(modelId);
            modelTotalStatistic.setUsageCount(1L);
            return modelTotalStatisticRepository.save(modelTotalStatistic);
        } else{
            modelTotalStatistic.setUsageCount(modelTotalStatistic.getUsageCount() + 1L);
            return modelTotalStatisticRepository.save(modelTotalStatistic);
        }

    }
}