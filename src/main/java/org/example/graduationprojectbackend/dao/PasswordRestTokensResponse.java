package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRestTokensResponse extends JpaRepository<PasswordResetToken, Long>
{
    /**
     * 根据重置密码令牌查找 PasswordResetToken 实体。
     *
     * @param token 重置密码令牌
     * @return 可选的 PasswordResetToken 对象
     */
    Optional<PasswordResetToken> findByToken(String token);

    /**
     * 根据用户 ID 查找该用户的 PasswordResetToken 实体。
     *
     * @param userId 用户的 ID
     * @return 可选的 PasswordResetToken 对象
     */
    Optional<PasswordResetToken> findByUserId(Long userId);

    /**
     * 根据用户和令牌查找 PasswordResetToken 实体。
     *
     * @param userId 用户的 ID
     * @param token  重置密码令牌
     * @return 可选的 PasswordResetToken 对象
     */
    Optional<PasswordResetToken> findByUserIdAndToken(Long userId, String token);
}
