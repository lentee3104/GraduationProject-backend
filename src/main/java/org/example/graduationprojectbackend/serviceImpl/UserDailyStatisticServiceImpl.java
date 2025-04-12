package org.example.graduationprojectbackend.serviceImpl;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.example.graduationprojectbackend.dao.UserDailyStatisticRepository;
import org.example.graduationprojectbackend.dao.UserRepository;
import org.example.graduationprojectbackend.entity.User;
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
    private final UserRepository userRepository;

    @Autowired
    public UserDailyStatisticServiceImpl(UserDailyStatisticRepository userDailyStatisticRepository, UserRepository userRepository) {
        this.userDailyStatisticRepository = userDailyStatisticRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDailyStatistic> getStatisticsByUserId(Long userId) {
        return userDailyStatisticRepository.findByUserId(userId);
    }

    @Override
    public UserDailyStatistic getStatisticByUserIdAndUsageData(Long userId, LocalDate usageDate) {
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

    @Override
    public UserDailyStatistic update(Long userId) {
        LocalDate localDate = LocalDate.now();
        UserDailyStatistic userDailyStatistic = userDailyStatisticRepository.findByUserIdAndUsageDate(userId, localDate);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        if (userDailyStatistic == null) {
            userDailyStatistic = new UserDailyStatistic();
            userDailyStatistic.setUser(user);
            userDailyStatistic.setUsageDate(localDate);
            userDailyStatistic.setUsageCount(1L);
            return saveUserDailyStatistic(userDailyStatistic);

        } else {
            userDailyStatistic.setUsageCount(userDailyStatistic.getUsageCount() + 1L);
            return userDailyStatisticRepository.save(userDailyStatistic);
        }
    }
}
