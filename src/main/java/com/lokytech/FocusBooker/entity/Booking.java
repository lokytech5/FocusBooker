package com.lokytech.FocusBooker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lokytech.FocusBooker.enums.ServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking {
    @jakarta.persistence.Id
    @GeneratedValue
    private Long Id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Email cannot be blank")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    @Future(message = "Date must be in the future")
    private LocalDate date;
    private LocalTime time;
    private String message;

    @ManyToOne
    @JsonBackReference
    private Users user;

    public Booking() {
    }

    public Booking(Long id, String name, String email, String phoneNumber, ServiceType serviceType, LocalDate date, LocalTime time, String message, Users user) {
        Id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.serviceType = serviceType;
        this.date = date;
        this.time = time;
        this.message = message;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
