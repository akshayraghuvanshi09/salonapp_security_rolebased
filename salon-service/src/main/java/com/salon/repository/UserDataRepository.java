package com.salon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.salon.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	  Optional<UserData> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
}
