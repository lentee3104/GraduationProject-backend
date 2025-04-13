package org.example.graduationprojectbackend.service;

import org.example.graduationprojectbackend.entity.ERole;
import org.example.graduationprojectbackend.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
    Role saveRole(Role role);
}
