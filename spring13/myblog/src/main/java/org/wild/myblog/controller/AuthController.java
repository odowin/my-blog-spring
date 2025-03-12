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

import org.wild.myblog.dto.UserLoginDTO;
import org.wild.myblog.security.AuthenticationService;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    //public AuthController(UserService userService) {
    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
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

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authenticationService.authenticate(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        );
        return ResponseEntity.ok(token);
    }
}
