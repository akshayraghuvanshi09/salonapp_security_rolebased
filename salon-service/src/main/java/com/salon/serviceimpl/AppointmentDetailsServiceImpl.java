package com.salon.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.salon.model.AppointmentDetails;
import com.salon.repository.AppointmentDetailsRepository;
import com.salon.service.AppointmentDetailsService;

public class AppointmentDetailsServiceImpl implements AppointmentDetailsService {

	@Autowired
	private AppointmentDetailsRepository appointmentDetailsRepository;
	
	@Override
	public ResponseEntity<?> addAppointment(AppointmentDetails appointmentDetails) {
		return null;
	}

	@Override
	public ResponseEntity<?> getAppointmentByEmpId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateAppointment(AppointmentDetails appointmentDetails, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getAppointmentByAppointmentID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getAppointmentByClinetID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> cancelAppointment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
