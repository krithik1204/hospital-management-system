package com.hospital.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.request.AppointmentRequest;
import com.hospital.dto.response.AppointmentResponse;
import com.hospital.entity.Appointment;
import com.hospital.exception.ValidationException;
import com.hospital.mapper.AppointmentMapper;
import com.hospital.repository.AppointmentRepository;
import com.hospital.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public AppointmentResponse create(AppointmentRequest appointmentRequest) {
	//	logger.info("Creating appointment for patient ID: {}", appointmentRequest.getPatientId());
		
		// Validate appointment request
		validateAppointmentRequest(appointmentRequest);
		
		try {
			Appointment appointment = new Appointment();
			BeanUtils.copyProperties(appointmentRequest, appointment);
			
			Appointment savedAppointment = appointmentRepository.save(appointment);
		//	logger.info("Appointment created successfully with ID: {}", savedAppointment.getId());
			
			return AppointmentMapper.responseMap(savedAppointment);
		} catch (Exception e) {
			logger.error("Error creating appointment: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to create appointment. Please try again.");
		}
	}

	@Override
	public List<AppointmentResponse> viewAll() {
		logger.info("Fetching all appointments");
		
		try {
			List<Appointment> appointments = appointmentRepository.findAll();
			logger.info("Retrieved {} appointments", appointments.size());
			
			return appointments.stream()
				.map(AppointmentMapper::responseMap)
				.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error fetching appointments: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to fetch appointments. Please try again.");
		}
	}
	
	/**
	 * Validate appointment request
	 */
	private void validateAppointmentRequest(AppointmentRequest request) {
		if (request == null) {
			throw new ValidationException("request", null, "Appointment request cannot be null");
		}
		
		/*
		 * if (request.getPatientId() == null || request.getPatientId() <= 0) { throw
		 * new ValidationException("patientId", request.getPatientId(),
		 * "Valid patient ID is required"); }
		 * 
		 * if (request.getDoctorId() == null || request.getDoctorId() <= 0) { throw new
		 * ValidationException("doctorId", request.getDoctorId(),
		 * "Valid doctor ID is required"); }
		 * 
		 * if (request.getAppointmentDate() == null) { throw new
		 * ValidationException("appointmentDate", null, "Appointment date is required");
		 * }
		 */
		/*
		 * if (request.getReason() == null || request.getReason().trim().isEmpty()) {
		 * throw new ValidationException("reason", null,
		 * "Appointment reason is required"); }
		 */
	}

}
