package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.UserRepository;
import org.example.graduationprojectbackend.dao.UserTotalStatisticRepository;
import org.example.graduationprojectbackend.entity.User;
import org.example.graduationprojectbackend.entity.UserTotalStatistic;
import org.example.graduationprojectbackend.service.UserTotalStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserTotalStatisticServiceImpl implements UserTotalStatisticService {

    private final UserTotalStatisticRepository userTotalStatisticRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserTotalStatisticServiceImpl(UserTotalStatisticRepository userTotalStatisticRepository, UserRepository userRepository) {
        this.userTotalStatisticRepository = userTotalStatisticRepository;
        this.userRepository = userRepository;
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

    @Override
    public UserTotalStatistic update(Long userId) {
        LocalDate localDate = LocalDate.now();
        UserTotalStatistic userTotalStatistic = userTotalStatisticRepository.findByUserId(userId);
        User user = userRepository.findById(userId).orElse(null);
        if (userTotalStatistic == null) {
            UserTotalStatistic userTotalStatisticTemp = new UserTotalStatistic();
            userTotalStatisticTemp.setUser(user);
            userTotalStatisticTemp.setLastUpdated(localDate);
            userTotalStatisticTemp.setUsageCount(1L);
            return userTotalStatisticRepository.save(userTotalStatisticTemp);
        } else {
            userTotalStatistic.setUsageCount(userTotalStatistic.getUsageCount() + 1);
            return userTotalStatisticRepository.save(userTotalStatistic);
        }
    }
}
