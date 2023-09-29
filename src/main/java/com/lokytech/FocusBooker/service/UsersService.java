package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.dto.UsersDTO;
import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.exception.UserNotFoundException;
import com.lokytech.FocusBooker.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }

    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }

    public Users findUserById(Long id){
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Users not found with id"));
    }

    public UsersDTO findUserProfile(Long id){
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        return modelMapper.map(users, UsersDTO.class);
    }
     public UsersDTO updateUserProfile(Long id, @Validated Map<String, Object> update){
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        if(update.containsKey("username")) users.setUsername((String) update.get("username"));
        if(update.containsKey("email")) users.setEmail((String) update.get("email"));

        Users updateUsers = usersRepository.save(users);
        return modelMapper.map(updateUsers, UsersDTO.class);
    }

    public void deleteUsersById(Long id){
        usersRepository.deleteById(id);
    }





}
