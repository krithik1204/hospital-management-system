package com.hospital.service;

import org.springframework.http.ResponseEntity;

import com.hospital.dto.request.PatientRegistrationRequest;
import com.hospital.dto.response.PatientResponse;

public interface PatientService {
	
	public PatientResponse register(PatientRegistrationRequest request);

}
