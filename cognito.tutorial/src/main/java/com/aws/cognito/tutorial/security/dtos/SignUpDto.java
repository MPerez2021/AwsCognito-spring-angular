package com.aws.cognito.tutorial.security.dtos;

import com.aws.cognito.tutorial.security.entities.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignUpDto {


    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    @NotEmpty(message = "El apellido es obligatorio")
    private String lastName;

    @NotEmpty(message = "El contraseña es obligatorio")
    private String password;

    @NotEmpty(message = "El email es obligatorio")
    private String email;

    private Role role;

}
