package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.entity.ModelDailyStatistic;
import org.example.graduationprojectbackend.entity.ModelTotalStatistic;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.example.graduationprojectbackend.entity.UserTotalStatistic;
import org.example.graduationprojectbackend.service.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateAllStatisticImpl implements UpdateAllStatisticService {
    private final ModelDailyStatisticService modelDailyStatisticService;
    private final ModelTotalStatisticService modelTotalStatisticService;
    private final UserDailyStatisticService userDailyStatisticService;
    private final UserTotalStatisticService userTotalStatisticService;

    public UpdateAllStatisticImpl(ModelDailyStatisticService modelDailyStatisticService, ModelTotalStatisticService modelTotalStatisticService, UserDailyStatisticService userDailyStatisticService, UserTotalStatisticService userTotalStatisticService) {
        this.modelDailyStatisticService = modelDailyStatisticService;
        this.modelTotalStatisticService = modelTotalStatisticService;
        this.userDailyStatisticService = userDailyStatisticService;
        this.userTotalStatisticService = userTotalStatisticService;
    }


    @Override
    public String updateAllStatistic(Long userId, Long modelId) {
        ModelDailyStatistic modelDailyStatistic = modelDailyStatisticService.update(modelId);
        ModelTotalStatistic modelTotalStatistic = modelTotalStatisticService.update(modelId);
        UserDailyStatistic userDailyStatistic = userDailyStatisticService.update(userId);
        UserTotalStatistic userTotalStatistic = userTotalStatisticService.update(userId);
        return "success";
    }
}
