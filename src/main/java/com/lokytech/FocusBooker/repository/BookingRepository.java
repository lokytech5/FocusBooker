package com.lokytech.FocusBooker.repository;

import com.lokytech.FocusBooker.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
