package com.lokytech.FocusBooker.repository;

import com.lokytech.FocusBooker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
