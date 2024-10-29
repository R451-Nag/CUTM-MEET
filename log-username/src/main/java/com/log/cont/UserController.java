package com.log.cont;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.log.model.UserActivity;
import com.log.repository.UserActivityRepository;

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
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        currentUser = new UserActivity();
        currentUser.setEmail(email);
        currentUser.setPassword(password);
        currentUser.setLoginTime(LocalDateTime.now());

        userActivityRepository.save(currentUser);

        model.addAttribute("email", email);
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", currentUser.getEmail());
        return "home";
    }
    
    @GetMapping("/activity")
    public String viewActivityData(Model model) {
        List<UserActivity> activities = userActivityRepository.findAll();
        model.addAttribute("activities", activities);
        return "activity";
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