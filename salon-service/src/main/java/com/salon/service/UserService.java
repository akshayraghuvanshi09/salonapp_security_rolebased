package com.salon.service;

import org.springframework.http.ResponseEntity;

import com.salon.request.SignupRequest;

public interface UserService {

	public ResponseEntity<?> newUser(SignupRequest signupRequest);

	public ResponseEntity<?> getUsers();

	public ResponseEntity<?> updateUser(SignupRequest signupRequest, Long id);

	public ResponseEntity<?> deleteUser(Long id);
}
