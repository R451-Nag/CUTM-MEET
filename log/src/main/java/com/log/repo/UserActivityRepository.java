package com.log.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}