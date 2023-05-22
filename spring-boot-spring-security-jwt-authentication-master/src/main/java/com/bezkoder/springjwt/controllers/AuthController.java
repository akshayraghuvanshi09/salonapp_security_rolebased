package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.RefreshTokenRequest;
import com.bezkoder.springjwt.payload.response.JwtAccessTokenResponse;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String accessToken = jwtUtils.generateJwtToken(userDetails);
		String refreshToken = jwtUtils.generateRefreshToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken, userDetails.getId(),
				userDetails.getUsername(), userDetails.getEmail(), roles, (jwtUtils.getExipirationTime() / 1000) / 60));
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest tokenRefreshRequest) {
		log.info("LoginController: refreshToken: request={}", tokenRefreshRequest);
		String refreshToken = tokenRefreshRequest.getRefreshToken();
		if (jwtUtils.validateJwtToken(refreshToken)) {
			String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			String newAccessToken = jwtUtils.generateRefreshToken(userDetails);

			return ResponseEntity.ok(new JwtAccessTokenResponse(newAccessToken, refreshToken));
		} else {
			return ResponseEntity.badRequest().body("Invalid refresh token");
		}
	}
}
