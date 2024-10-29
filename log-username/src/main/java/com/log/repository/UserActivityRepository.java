package com.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    UserActivity findByEmailAndPassword(String email, String password); // Method to find user by email and password
}
