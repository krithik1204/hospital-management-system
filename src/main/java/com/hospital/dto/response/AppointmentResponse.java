package com.hospital.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {

	private String email;

	private String doctorName;

	private LocalDateTime registrationDate;
	private String specialization;

}
