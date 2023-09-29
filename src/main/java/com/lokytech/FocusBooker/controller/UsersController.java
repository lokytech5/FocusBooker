package com.lokytech.FocusBooker.controller;

import com.lokytech.FocusBooker.dto.UsersDTO;
import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.exception.UserNotFoundException;
import com.lokytech.FocusBooker.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> GetAllUsers(){
        List<Users> users = usersService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try{
            Users users = usersService.findUserById(userId);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        users = usersService.saveUsers(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/profile")
    public ResponseEntity<UsersDTO> getUserProfile(@PathVariable Long userId){
        UsersDTO usersDTO = usersService.findUserProfile(userId);
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
}
