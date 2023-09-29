package com.lokytech.FocusBooker.controller;

import com.lokytech.FocusBooker.dto.LoginDTO;
import com.lokytech.FocusBooker.dto.UsersDTO;
import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.exception.RoleNotFoundException;
import com.lokytech.FocusBooker.security.JwtService;
import com.lokytech.FocusBooker.service.UsersService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private final UsersService usersService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private Logger logger;

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
            UsersDTO savedUser = usersService.saveUser(user, role.toUpperCase(), encoder);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            // Authenticate user.
            // This will internally use the `loadUserByUsername` method of the `UsersService`
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate token
            Users user = (Users) authentication.getPrincipal();
            logger.debug("User roles after authentication: {}", user.getAuthorities());
            List<String> roles = user.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtService.generateToken(user.getUsername(), roles);
            logger.debug("Generating token for user: {}, with roles: {}", user, roles);

            // Return token
            Map<String, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("token", token);
            return new ResponseEntity<String>("Login Successfully", HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
