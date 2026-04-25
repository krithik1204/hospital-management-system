package com.hospital.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.dto.request.PatientRegistrationRequest;
import com.hospital.dto.response.PatientResponse;
import com.hospital.entity.Patient;
import com.hospital.entity.User;
import com.hospital.mapper.PatientMapper;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;
import com.hospital.service.UserService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public PatientResponse register(PatientRegistrationRequest request) {
		
	User user=	userService.createUser(request.getEmail(),request.getPassword());
		
		Patient patient = new Patient();
		BeanUtils.copyProperties(request, patient);
        patient.setUserId(user.getId());
		return PatientMapper.responseMap(patientRepository.save(patient));
	}

}
