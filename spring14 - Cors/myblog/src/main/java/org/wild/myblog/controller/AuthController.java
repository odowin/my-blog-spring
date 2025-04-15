package org.wild.myblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wild.myblog.dto.UserRegistrationDTO;
import org.wild.myblog.model.User;
import org.wild.myblog.service.UserService;

import org.wild.myblog.dto.UserLoginDTO;
import org.wild.myblog.security.AuthenticationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    //public ResponseEntity<String> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authenticationService.authenticate(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        );
        //return ResponseEntity.ok(token);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
