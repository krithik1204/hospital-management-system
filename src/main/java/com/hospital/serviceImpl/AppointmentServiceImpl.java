package com.hospital.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hospital.dto.request.AppointmentRequest;
import com.hospital.dto.response.AppointmentResponse;
import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.mapper.AppointmentMapper;
import com.hospital.repository.AppointmentRepository;
import com.hospital.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	

	@Override
	public AppointmentResponse create(AppointmentRequest appointmentRequest) {

		Appointment appointment = new Appointment();
		BeanUtils.copyProperties(appointmentRequest, appointment);
		// TODO Auto-generated method stub
		return AppointmentMapper.responseMap(appointmentRepository.save(appointment));

	}


	@Override
	public List<AppointmentResponse> viewAll() {
		// TODO Auto-generated method stub
		
	List<Appointment> list=	appointmentRepository.findAll();
return	list.stream().map(appointment->AppointmentMapper.responseMap(appointment)).collect(Collectors.toList());
		
	}

}
