package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	PatientService patientService;

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> register(@Valid @RequestBody PatientRegistrationRequest registrationRequest) {
		PatientResponse patientResponse = patientService.register(registrationRequest);

		return ResponseEntity.ok(patientResponse);

	}

}
