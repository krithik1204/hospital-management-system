package com.hospital.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.request.PatientRegistrationRequest;
import com.hospital.dto.response.PatientResponse;
import com.hospital.entity.Patient;
import com.hospital.entity.User;
import com.hospital.exception.ValidationException;
import com.hospital.mapper.PatientMapper;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;
import com.hospital.service.UserService;

@Service
public class PatientServiceImpl implements PatientService {

	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public PatientResponse register(PatientRegistrationRequest request) {
		logger.info("Processing patient registration for email: {}", request.getEmail());
		
		// Validate request
		validatePatientRegistrationRequest(request);
		
		try {
			// Create user account first
			User user = userService.createUser(request.getEmail(), request.getPassword());
			
			// Create patient record
			Patient patient = new Patient();
			BeanUtils.copyProperties(request, patient);
			patient.setUserId(user.getId());
			
			Patient savedPatient = patientRepository.save(patient);
			logger.info("Patient registered successfully with email: {}", request.getEmail());
			
			return PatientMapper.responseMap(savedPatient);
		} catch (RuntimeException e) {
			logger.error("Error during patient registration: {}", e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error during patient registration: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to register patient. Please try again.");
		}
	}
	
	/**
	 * Validate patient registration request
	 */
	private void validatePatientRegistrationRequest(PatientRegistrationRequest request) {
		if (request == null) {
			throw new ValidationException("request", null, "Registration request cannot be null");
		}
		
		if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
			throw new ValidationException("email", null, "Email is required");
		}
		
		if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
			throw new ValidationException("password", null, "Password is required");
		}
		
		if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
			throw new ValidationException("firstName", null, "First name is required");
		}
		
		if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
			throw new ValidationException("lastName", null, "Last name is required");
		}
	}

}
