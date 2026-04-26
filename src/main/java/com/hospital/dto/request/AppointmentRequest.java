package com.hospital.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentRequest {
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;


	@NotBlank(message = "Doctor Name is required")
	private String doctorName; // This should be encrypted using BCrypt
	@NotNull(message = "Date is required")
	private LocalDateTime registrationDate;
	@NotBlank(message = "Specialization is not blank")
	private String specialization;

}
