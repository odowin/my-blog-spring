package org.wild.myblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wild.myblog.dto.UserRegistrationDTO;
import org.wild.myblog.model.User;
import org.wild.myblog.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        User registeredUser = userService.resgisterUser(
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword(),
                Set.of("ROLE_USER")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
