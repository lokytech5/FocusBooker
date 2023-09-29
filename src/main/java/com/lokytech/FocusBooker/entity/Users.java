package com.lokytech.FocusBooker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "username can not be Empty")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email can not be Empty")
    private String email;

    @NotBlank(message = "Password field can be Empty")
    private String password;

    public Users() {
    }

    public Users(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
