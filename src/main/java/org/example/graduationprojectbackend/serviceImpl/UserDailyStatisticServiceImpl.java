package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.UserDailyStatisticRepository;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.example.graduationprojectbackend.service.UserDailyStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDailyStatisticServiceImpl implements UserDailyStatisticService {

    private final UserDailyStatisticRepository userDailyStatisticRepository;

    @Autowired
    public UserDailyStatisticServiceImpl(UserDailyStatisticRepository userDailyStatisticRepository) {
        this.userDailyStatisticRepository = userDailyStatisticRepository;
    }

    @Override
    public List<UserDailyStatistic> getStatisticsByUserId(Long userId) {
        return userDailyStatisticRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserDailyStatistic> getStatisticByUserIdAndDate(Long userId, String data) {
        return userDailyStatisticRepository.findByUserIdAndData(userId, data);
    }

    @Override
    public List<UserDailyStatistic> getStatisticsByDate(String data) {
        return userDailyStatisticRepository.findByData(data);
    }

    @Override
    public UserDailyStatistic saveUserDailyStatistic(UserDailyStatistic userDailyStatistic) {
        return userDailyStatisticRepository.save(userDailyStatistic);
    }

    @Override
    public void deleteUserDailyStatistic(Long id) {
        userDailyStatisticRepository.deleteById(id);
    }
}
