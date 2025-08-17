package com.example.demo1.openFiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo1.NotificationResponse.NotificationEvent;
import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;

@FeignClient(name="patients-service")
public interface PatientsFiegn {
	
	@GetMapping("/patients/v1/patient/{id}")
	public Patient_Details_To_Admin getPatientById(@PathVariable("id") int id);

}
