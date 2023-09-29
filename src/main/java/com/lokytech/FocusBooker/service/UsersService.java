package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.dto.UsersDTO;
import com.lokytech.FocusBooker.entity.Roles;
import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.exception.RoleNotFoundException;
import com.lokytech.FocusBooker.exception.UserNotFoundException;
import com.lokytech.FocusBooker.repository.RoleRepository;
import com.lokytech.FocusBooker.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return user;
    }

    public Users saveUser(Users users, String role, BCryptPasswordEncoder bCryptPasswordEncoder){
        Roles roleEntity = roleRepository.findByName(role)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRoles(new HashSet<>(Arrays.asList(roleEntity)));
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
