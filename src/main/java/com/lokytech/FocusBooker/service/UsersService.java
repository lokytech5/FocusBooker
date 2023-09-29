package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }


}
