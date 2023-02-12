package com.aws.cognito.tutorial.security.controller;

import com.aws.cognito.tutorial.dtos.ResponseMessageDto;
import com.aws.cognito.tutorial.security.dtos.SignInDto;
import com.aws.cognito.tutorial.security.dtos.SignUpDto;
import com.aws.cognito.tutorial.security.entities.User;
import com.aws.cognito.tutorial.security.services.UserDetailsServiceImpl;
import com.aws.cognito.tutorial.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMessageDto> signUpUser(@RequestBody @Valid SignUpDto signUpDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(new ResponseMessageDto(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        userService.signUpUser(signUpDto);
        return new ResponseEntity<ResponseMessageDto>(new ResponseMessageDto("Se ha registrado el usuario con éxito"), HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseMessageDto> signInUser(@RequestBody @Valid SignInDto signInDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(new ResponseMessageDto(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        String accessToken = userService.signInUser(signInDto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(signInDto.getEmail());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<ResponseMessageDto>(new ResponseMessageDto(accessToken), HttpStatus.OK);
    }


    @GetMapping("/user-details")
    public Optional<User> getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }
}
