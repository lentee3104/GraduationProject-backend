package org.example.graduationprojectbackend.serviceImpl;

import org.example.graduationprojectbackend.dao.RoleRepository;
import org.example.graduationprojectbackend.entity.ERole;
import org.example.graduationprojectbackend.entity.Role;
import org.example.graduationprojectbackend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
