package com.salon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.model.ERoleData;
import com.salon.model.RoleData;

public interface RoleDataRepository extends JpaRepository<RoleData, Long> {
	 Optional<RoleData> findByName(ERoleData name);
}
