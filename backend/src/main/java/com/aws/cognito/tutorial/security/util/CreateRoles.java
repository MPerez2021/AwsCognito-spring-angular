package com.aws.cognito.tutorial.security.util;

import com.aws.cognito.tutorial.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.createRoles();
    }
}
**/