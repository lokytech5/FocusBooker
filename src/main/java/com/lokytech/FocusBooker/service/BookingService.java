package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.repository.BookingRepository;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingService {

    private BookingRepository bookingRepository;

    private UsersService usersService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UsersService usersService) {
        this.bookingRepository = bookingRepository;
        this.usersService = usersService;
    }



}
