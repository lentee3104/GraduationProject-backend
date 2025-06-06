package org.example.graduationprojectbackend.dao;

import org.example.graduationprojectbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long userId);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
