package com.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.log.model.UserActivity;
import com.log.repo.UserActivityRepository;

import java.time.LocalDateTime;

@Controller
public class UserController {

    @Autowired
    private UserActivityRepository userActivityRepository;

    private UserActivity currentUser;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, Model model) {
        currentUser = new UserActivity();
        currentUser.setUsername(username);
        currentUser.setLoginTime(LocalDateTime.now());
        userActivityRepository.save(currentUser);

        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", currentUser.getUsername());
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (currentUser != null) {
            currentUser.setLogoutTime(LocalDateTime.now());
            userActivityRepository.save(currentUser);
            currentUser = null;
        }
        return "redirect:/login";
    }
}