package com.lokytech.FocusBooker.service;

import com.lokytech.FocusBooker.dto.BookingDTO;
import com.lokytech.FocusBooker.dto.UsersDTO;
import com.lokytech.FocusBooker.entity.Booking;
import com.lokytech.FocusBooker.entity.Users;
import com.lokytech.FocusBooker.repository.BookingRepository;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    public List<Booking> findAllBooking(){
        return bookingRepository.findAll();
    }

    public Optional<Booking> findBookingById(Long id){
        return bookingRepository.findById(id);
    }

    public BookingDTO saveBooking(Long userId, Booking booking){
        Users user = usersService.findUserById(userId);
        booking.setUser(user);

        Booking savedBooking = bookingRepository.save(booking);
        BookingDTO dto = modelMapper.map(savedBooking, BookingDTO.class);

        if (user != null) {
            UsersDTO userDTO = modelMapper.map(user, UsersDTO.class);
            dto.setUsersDTO(userDTO);
        }

        return dto;
    }





}
