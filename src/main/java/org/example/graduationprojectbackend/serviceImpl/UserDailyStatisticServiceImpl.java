package org.example.graduationprojectbackend.serviceImpl;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.example.graduationprojectbackend.dao.UserDailyStatisticRepository;
import org.example.graduationprojectbackend.dao.UserRepository;
import org.example.graduationprojectbackend.dto.UserDailyUsageDto;
import org.example.graduationprojectbackend.entity.User;
import org.example.graduationprojectbackend.entity.UserDailyStatistic;
import org.example.graduationprojectbackend.service.UserDailyStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<UserDailyUsageDto> getStatisticsByUserId(Long userId) {
        List<UserDailyStatistic> userDailyStatisticList = userDailyStatisticRepository.findByUserId(userId);
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userId));
        // 使用 Java 8 Stream API 将 UserDailyStatistic 转换为 UserDailyUsageDto
        List<UserDailyUsageDto> userDailyUsageDtoList = userDailyStatisticList.stream()
                .map(statistic -> {
                    // 创建一个新的 UserDailyUsageDto 对象
                    UserDailyUsageDto dto = new UserDailyUsageDto();
                    dto.setId(statistic.getId());
                    dto.setDailyCount(statistic.getUsageCount());
                    dto.setUsageDate(statistic.getUsageDate());
                    dto.setUserId(user.getId());
                    dto.setUserName(user.getUsername());
                    return dto;  // 返回转换后的 DTO
                })
                .collect(Collectors.toList());  // 将转换后的结果收集到一个列表中

        return userDailyUsageDtoList;
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
            userDailyStatistic.setUsageCount(1L);
            userDailyStatistic.setUsageDate(localDate);
            return saveUserDailyStatistic(userDailyStatistic);

        } else {
            userDailyStatistic.setUsageCount(userDailyStatistic.getUsageCount() + 1L);
            return userDailyStatisticRepository.save(userDailyStatistic);
        }
    }
}
