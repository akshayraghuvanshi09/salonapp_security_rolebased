package com.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salon.model.AppointmentDetails;
@Repository
public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Long>{

}
