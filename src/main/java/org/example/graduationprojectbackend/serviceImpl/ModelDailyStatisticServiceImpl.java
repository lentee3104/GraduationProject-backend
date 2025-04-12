package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.ModelDailyStatisticRepository;
import org.example.graduationprojectbackend.entity.ModelDailyStatistic;
import org.example.graduationprojectbackend.service.ModelDailyStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ModelDailyStatisticServiceImpl implements ModelDailyStatisticService {

    private final ModelDailyStatisticRepository modelDailyStatisticRepository;

    @Autowired
    public ModelDailyStatisticServiceImpl(ModelDailyStatisticRepository modelDailyStatisticRepository) {
        this.modelDailyStatisticRepository = modelDailyStatisticRepository;
    }


    @Override
    public List<ModelDailyStatistic> findAll() {
        return modelDailyStatisticRepository.findAll();
    }

    @Override
    public ModelDailyStatistic save(ModelDailyStatistic modelDailyStatistic) {
        return modelDailyStatisticRepository.save(modelDailyStatistic);
    }

    @Override
    public ModelDailyStatistic update(Long modelId) {
        LocalDate localDate = LocalDate.now();
        ModelDailyStatistic modelDailyStatistic = modelDailyStatisticRepository.findByModelIdAndUsageDate(modelId, localDate);
        // 如果找不到记录，则新建一个
        if (modelDailyStatistic == null) {
            modelDailyStatistic = new ModelDailyStatistic();
            modelDailyStatistic.setUsageDate(localDate);
            modelDailyStatistic.setUsageCount(1L);  // 新建时初始化 usageCount 为 1// 假设 ModelDailyStatistic 中有 userId 字段

            // 保存新建的记录
            return modelDailyStatisticRepository.save(modelDailyStatistic);
        } else {
            // 如果记录已存在，增加 usageCount
            modelDailyStatistic.setUsageCount(modelDailyStatistic.getUsageCount() + 1);

            // 保存更新后的记录
            return modelDailyStatisticRepository.save(modelDailyStatistic);
        }
    }
}
