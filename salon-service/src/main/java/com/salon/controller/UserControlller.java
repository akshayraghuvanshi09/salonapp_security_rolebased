package com.salon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.request.SignupRequest;
import com.salon.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/salon/user")
public class UserControlller {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('STAF') or hasRole('RECEPTIONIST') or hasRole('ADMIN')")
	@GetMapping("/get")
	public ResponseEntity<?> getAllUsers() {
		return userService.getUsers();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		return userService.newUser(signUpRequest);
	}

	@PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	
	@PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody SignupRequest signupRequest, @PathVariable("id") Long id) {
		return userService.updateUser(signupRequest, id);
	}

}
