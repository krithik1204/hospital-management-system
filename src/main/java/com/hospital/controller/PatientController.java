package com.hospital.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.request.PatientRegistrationRequest;
import com.hospital.dto.response.PatientResponse;
import com.hospital.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	PatientService patientService;

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PatientResponse> register(@Valid @RequestBody PatientRegistrationRequest registrationRequest) {
		logger.info("Patient registration request received for email: {}", registrationRequest.getEmail());
		
		PatientResponse patientResponse = patientService.register(registrationRequest);
		logger.info("Patient registration successful for email: {}", registrationRequest.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
	}

}
