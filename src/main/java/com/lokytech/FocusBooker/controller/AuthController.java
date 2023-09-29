package com.lokytech.FocusBooker.controller;

import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.exception.RoleNotFoundException;
import com.lokytech.FocusBooker.security.JwtService;
import com.lokytech.FocusBooker.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {
    private final UsersService usersService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UsersService usersService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register/{role}")
    public ResponseEntity<?> createUser(@PathVariable String role, @RequestBody Users user) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Users savedUser = usersService.saveUser(user, role.toUpperCase(), encoder);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
