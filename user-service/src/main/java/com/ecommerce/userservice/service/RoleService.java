package com.ecommerce.userservice.service;

import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name)
    {
        Role role=new Role();
        role.setRole(name);
        return roleRepository.save(role);

    }

}
