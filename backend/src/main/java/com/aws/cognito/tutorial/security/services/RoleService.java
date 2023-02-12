package com.aws.cognito.tutorial.security.services;

import com.aws.cognito.tutorial.security.entities.Role;
import com.aws.cognito.tutorial.security.enums.RoleName;
import com.aws.cognito.tutorial.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRoles(){
        Role user = new Role(RoleName.ROLE_USER);
        Role admin = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(user);
        roleRepository.save(admin);
    }

    public Optional<Role> getRoleByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
