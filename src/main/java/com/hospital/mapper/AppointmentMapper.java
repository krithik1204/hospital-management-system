package com.hospital.mapper;

import com.hospital.dto.response.AppointmentResponse;
import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;

public class AppointmentMapper {
	
	public static AppointmentResponse responseMap(Appointment appointment) {
		
		if(appointment == null) {
			return null;
		}
		else {
			
			
			AppointmentResponse response=	AppointmentResponse.builder().doctorName(appointment.getDoctorName())
			                             .email(appointment.getEmail())
			                             .registrationDate(appointment.getRegistrationDate())
			                             .specialization(appointment.getSpecialization()).build();
			
			return response;
			
		}
	}

}
