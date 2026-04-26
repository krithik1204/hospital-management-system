package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String doctorName; // This should be encrypted using BCrypt

	@CreationTimestamp
	private LocalDateTime registrationDate;

	@Column(nullable = false)
	private String specialization;
}
