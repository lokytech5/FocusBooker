package com.lokytech.FocusBooker.controller;

import com.lokytech.FocusBooker.dto.BookingDTO;
import com.lokytech.FocusBooker.entity.Booking;
import com.lokytech.FocusBooker.service.BookingService;
import com.lokytech.FocusBooker.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/booking")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getAllBooking(){
        List<Booking> bookingList = bookingService.findAllBooking();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/booking")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingDTO> createBooking(@PathVariable Long userId, @Valid @RequestBody Booking booking){
        BookingDTO savedBooking = bookingService.saveBooking(userId, booking);
        return  new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        Optional<Booking> booking = bookingService.findBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
