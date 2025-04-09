package org.example.graduationprojectbackend.serviceImpl;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.example.graduationprojectbackend.dao.UserDailyStatisticRepository;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.example.graduationprojectbackend.service.UserDailyStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Optional<UserDailyStatistic> getStatisticByUserIdAndUsageData(Long userId, LocalDate usageDate) {
        return userDailyStatisticRepository.findByUserIdAndUsageDate(userId, usageDate);
    }

    @Override
    public List<UserDailyStatistic> getStatisticsByUsageData(LocalDate usageDate) {
        return userDailyStatisticRepository.findByUsageDate(usageDate);
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
