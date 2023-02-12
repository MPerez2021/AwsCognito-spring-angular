package com.aws.cognito.tutorial.security.controller;

import com.aws.cognito.tutorial.dtos.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/user-message")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseMessageDto> userMessage() {
        return new ResponseEntity<>(new ResponseMessageDto("El usuario tiene el rol usuario"), HttpStatus.OK);
    }

    @GetMapping("/admin-message")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseMessageDto> adminMessage() {
        return new ResponseEntity<>(new ResponseMessageDto("El usuario tiene el rol admin"), HttpStatus.OK);
    }
}
