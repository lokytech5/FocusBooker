package com.lokytech.FocusBooker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @GetMapping("/hello")
    public String printHello(){
        return "hello welcome to focus booker";
    }
}
