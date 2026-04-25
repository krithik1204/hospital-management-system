package com.hospital.mapper;

import com.hospital.dto.response.PatientResponse;
import com.hospital.entity.Patient;

public class PatientMapper {
	
	public static PatientResponse responseMap(Patient patient) {
		
		if(patient == null) {
			return null;
		}
		else {
			PatientResponse response=new PatientResponse();
			
			response.setEmail(patient.getEmail());
			
			return response;
			
		}
	}

}
