package com.salon.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salon.model.ERoleData;
import com.salon.model.RoleData;
import com.salon.model.UserData;
import com.salon.repository.RoleDataRepository;
import com.salon.repository.UserDataRepository;
import com.salon.request.SignupRequest;
import com.salon.response.MessageResponse;
import com.salon.service.UserService;
import com.salon.utils.Constants;
import com.salon.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDataRepository userDataRepository;
	@Autowired
	private RoleDataRepository roleDataRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<?> newUser(SignupRequest signupRequest) {
		log.info("UserServiceImpl::newUser::SingnupRequest Data {}" + signupRequest);
		if (userDataRepository.existsByUsername(signupRequest.getUsername())) {
			log.info("UserServiceImpl::newUser::USER NAME ALREADY EXITS");
			return ResponseEntity.badRequest().body(new MessageResponse(Messages.USER_NAME_ALREADY_EXITS));
		}

		if (userDataRepository.existsByEmail(signupRequest.getEmail())) {
			log.info("UserServiceImpl::newUser::USER EMAIL ALREADY EXITS");
			return ResponseEntity.badRequest().body(new MessageResponse(Messages.EMAIL_ALRESDY_EXISTS));
		}

		// Create new user's account
		UserData user = new UserData(signupRequest.getUsername(), signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()));

		Set<String> strRoles = signupRequest.getRole();
		System.out.println(signupRequest.getRole());
		log.info("UserServiceImpl::newUser::Roles {}" + strRoles);
		Set<RoleData> roles = new HashSet<>();

		if (strRoles == null) {
			RoleData userRole = roleDataRepository.findByName(ERoleData.ROLE_STAF)
					.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleData adminRole = roleDataRepository.findByName(ERoleData.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(adminRole);

					break;
				case "receptionist":
					RoleData modRole = roleDataRepository.findByName(ERoleData.ROLE_RECEPTIONIST)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(modRole);

					break;
				default:
					RoleData userRole = roleDataRepository.findByName(ERoleData.ROLE_STAF)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userDataRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(Messages.USER_REGITER_SUCCESSFULLY, Constants.OK));
	}

	@Override
	public ResponseEntity<?> getUsers() {
		List<UserData> userDatas = userDataRepository.findAll();
		if (!userDatas.isEmpty()) {
			log.info("UserServiceImpl::getUsers");
			return ResponseEntity.ok(userDatas);
		}
		log.info("UserServiceImpl::getUsers::DATA NOT FOUND");
		return ResponseEntity.badRequest().body(new MessageResponse(Messages.DATA_NOT_FOUND, Constants.NOT_FOUND));
	}

	@Override
	public ResponseEntity<?> updateUser(SignupRequest signupRequest, Long id) {
		UserData userData = userDataRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(Messages.USER_DOES_NOT_EXISTS));
		userData.setId(userData.getId());
		userData.setEmail(signupRequest.getEmail());
		userData.setUsername(signupRequest.getUsername());
		userData.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		//
		Set<String> strRoles = signupRequest.getRole();
		System.out.println(signupRequest.getRole());
		log.info("UserServiceImpl::newUser::Roles {}" + strRoles);
		Set<RoleData> roles = new HashSet<>();

		if (strRoles == null) {
			RoleData userRole = roleDataRepository.findByName(ERoleData.ROLE_STAF)
					.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleData adminRole = roleDataRepository.findByName(ERoleData.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(adminRole);

					break;
				case "receptionist":
					RoleData modRole = roleDataRepository.findByName(ERoleData.ROLE_RECEPTIONIST)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(modRole);

					break;
				default:
					RoleData userRole = roleDataRepository.findByName(ERoleData.ROLE_STAF)
							.orElseThrow(() -> new RuntimeException(Messages.USER_ROLE_NOT_EXISTS));
					roles.add(userRole);
				}
			});
		}

		userData.setRoles(roles);
		userDataRepository.save(userData);

		return ResponseEntity.ok(new MessageResponse(Messages.UPDATED_DATA_SUCCESSFULLY, Constants.OK));
	}

	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		Optional<UserData> byId = userDataRepository.findById(id);
		if (byId.isPresent()) {
			userDataRepository.deleteById(id);
			return ResponseEntity.ok().body(new MessageResponse(Messages.SUCCESS, Constants.OK));
		}

		return ResponseEntity.badRequest().body(new MessageResponse(Messages.DATA_NOT_FOUND, Constants.NOT_FOUND));
	}

}
