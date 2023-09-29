package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
}
