package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.UserTotalStatisticRepository;
import org.example.graduationprojectbackend.entity.UserTotalStatistic;
import org.example.graduationprojectbackend.service.UserTotalStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTotalStatisticServiceImpl implements UserTotalStatisticService {

    private final UserTotalStatisticRepository userTotalStatisticRepository;

    @Autowired
    public UserTotalStatisticServiceImpl(UserTotalStatisticRepository userTotalStatisticRepository) {
        this.userTotalStatisticRepository = userTotalStatisticRepository;
    }

    @Override
    public UserTotalStatistic getTotalStatisticByUserId(Long userId) {
        return userTotalStatisticRepository.findByUserId(userId);
    }

    @Override
    public UserTotalStatistic saveUserTotalStatistic(UserTotalStatistic userTotalStatistic) {
        return userTotalStatisticRepository.save(userTotalStatistic);
    }

    @Override
    public void deleteUserTotalStatistic(Long id) {
        userTotalStatisticRepository.deleteById(id);
    }
}
