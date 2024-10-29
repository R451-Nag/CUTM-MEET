package com.log.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.model.UserActivity;
import com.log.repository.UserActivityRepository;

@Service
public class UserService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    public UserActivity logUserActivity(String email, String password) {
        UserActivity userActivity = new UserActivity();
        userActivity.setEmail(email);
        userActivity.setPassword(password); // Storing password directly (for testing purposes)
        userActivity.setLoginTime(LocalDateTime.now());
        return userActivityRepository.save(userActivity);
    }

    public void logUserLogout(Long userId) {
        userActivityRepository.findById(userId).ifPresent(userActivity -> {
            userActivity.setLogoutTime(LocalDateTime.now());
            userActivityRepository.save(userActivity);
        });
    }
}