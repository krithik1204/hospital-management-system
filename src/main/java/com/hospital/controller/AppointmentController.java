package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.request.AppointmentRequest;
import com.hospital.dto.request.PatientRegistrationRequest;
import com.hospital.dto.response.AppointmentResponse;
import com.hospital.dto.response.PatientResponse;
import com.hospital.service.AppointmentService;
import com.hospital.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody AppointmentRequest appointmentRequest) {
		AppointmentResponse AppointmentResponse = appointmentService.create(appointmentRequest);

		return ResponseEntity.ok(AppointmentResponse);

	}
	
	@GetMapping(value = "/viewAll", produces = "application/json")
	public ResponseEntity<?> allAppontments() {
		List<AppointmentResponse> appointmentResponse = appointmentService.viewAll();

		return ResponseEntity.ok(appointmentResponse);

	}

}
