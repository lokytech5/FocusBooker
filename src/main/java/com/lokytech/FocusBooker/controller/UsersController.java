package com.lokytech.FocusBooker.controller;

import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> GetAllUsers(){
        List<Users> users = usersService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        users = usersService.saveUsers(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }
}
