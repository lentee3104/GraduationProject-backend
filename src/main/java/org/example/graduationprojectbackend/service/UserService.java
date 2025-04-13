package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long userId);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAllUsers();
    void deleteUserById(Long userId);
    User updateUser(User user);
    User save(User user);
}
