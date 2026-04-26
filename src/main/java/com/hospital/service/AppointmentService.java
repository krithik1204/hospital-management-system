package com.hospital.service;

import java.util.List;

import com.hospital.dto.request.AppointmentRequest;
import com.hospital.dto.response.AppointmentResponse;
import com.hospital.dto.response.PatientResponse;

public interface AppointmentService {

	AppointmentResponse create(AppointmentRequest appointmentRequest);
	
	List<AppointmentResponse> viewAll();

}
