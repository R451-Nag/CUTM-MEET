package com.LoginRegister.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LoginRegister.example.entity.Users;
import com.LoginRegister.example.requests.LoginRequest;
import com.LoginRegister.example.service.UserService;

@RestController
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register") 
	@CrossOrigin(origins = "http://localhost:4200")
	public Users addUser(@RequestBody Users user) {
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public Boolean loginUser(@RequestBody LoginRequest loginRequest) {
		return userService.loginUser(loginRequest);
		
   }

}
