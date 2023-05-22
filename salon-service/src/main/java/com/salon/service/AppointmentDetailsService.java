package com.salon.service;

import org.springframework.http.ResponseEntity;

import com.salon.model.AppointmentDetails;

public interface AppointmentDetailsService {

	public ResponseEntity<?> addAppointment(AppointmentDetails appointmentDetails);
	public ResponseEntity<?> getAppointmentByEmpId(Long userId);
	public ResponseEntity<?> updateAppointment(AppointmentDetails appointmentDetails,Long id);
	public ResponseEntity<?> getAppointmentByAppointmentID(Long id);
	public ResponseEntity<?> getAppointmentByClinetID(Long id);
	public ResponseEntity<?> cancelAppointment(Long id);
}
