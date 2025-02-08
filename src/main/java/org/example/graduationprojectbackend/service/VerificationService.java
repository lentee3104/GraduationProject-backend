package org.example.graduationprojectbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailService emailService;

    private static final String EMAIL_CODE_PREFIX = "email:code:";
    private static final long CODE_EXPIRE_MINUTES = 20;

    public void sendVerificationCode(String email) {
        String code = generateVerificationCode();
        // 将验证码存储到Redis，设置5分钟过期
        redisTemplate.opsForValue().set(
                EMAIL_CODE_PREFIX + email,
                code,
                CODE_EXPIRE_MINUTES,
                TimeUnit.MINUTES
        );
        // 发送验证码到邮箱
        emailService.sendVerificationCode(email, code);
    }

    public boolean verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(EMAIL_CODE_PREFIX + email);
        if (storedCode != null && storedCode.equals(code)) {
            redisTemplate.delete(EMAIL_CODE_PREFIX + email);
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        // 生成6位数字验证码
        return String.format("%06d", random.nextInt(1000000));
    }
}