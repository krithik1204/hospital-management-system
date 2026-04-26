package com.hospital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.request.AppointmentRequest;
import com.hospital.dto.response.AppointmentResponse;
import com.hospital.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
	
	@Autowired
	AppointmentService appointmentService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody AppointmentRequest appointmentRequest) {
		//logger.info("Creating appointment for patient ID: {} and doctor ID: {}", 
			//appointmentRequest.getPatientId(), appointmentRequest.getDoctorId());
		
		AppointmentResponse appointmentResponse = appointmentService.create(appointmentRequest);
		//logger.info("Appointment created successfully with ID: {}", appointmentResponse.getId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
	}
	
	@GetMapping(value = "/viewAll", produces = "application/json")
	public ResponseEntity<List<AppointmentResponse>> allAppointments() {
		logger.info("Fetching all appointments");
		
		List<AppointmentResponse> appointmentResponses = appointmentService.viewAll();
		logger.info("Retrieved {} appointments", appointmentResponses.size());
		
		return ResponseEntity.ok(appointmentResponses);
	}

}
