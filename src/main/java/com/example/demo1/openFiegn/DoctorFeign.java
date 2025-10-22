package com.example.demo1.openFiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo1.model.DoctorDetailsToAppointment;

@FeignClient(name="doctor-service")
public interface DoctorFeign {
	
	@GetMapping("/doctor/check/{id}")
	public DoctorDetailsToAppointment sendDoctorDetailsToAppointment(@PathVariable("id") int id);

}
