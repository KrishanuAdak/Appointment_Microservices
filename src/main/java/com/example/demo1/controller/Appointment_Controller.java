package com.example.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.NotificationResponse.NotificationEvent;
import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.model.Appointment_book_by_Patient;
import com.example.demo1.openFiegn.PatientsFiegn;
import com.example.demo1.repo.AppointmentRepo;
import com.example.demo1.service.Appointment_booked_service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/appointment")
public class Appointment_Controller {
	@Autowired
	private Appointment_booked_service service;
	
	@Autowired
	private AppointmentRepo repo;
	
	@Autowired(required=true)
	private PatientsFiegn feign;
	
	@PostMapping("/v1/schedule")	
	public ResponseEntity<?> bookAppointment(@Valid @RequestBody Appointment_book_by_Patient data ,BindingResult result,@RequestHeader("X-User-Id") String userid,
	        @RequestHeader("X-Role") String role){		

		if(result.hasErrors()) {
			System.out.println(result.getFieldErrors());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");
		}
		if(role.equalsIgnoreCase("patient"))
		{
			Appointment_book_by_Patient data1=new Appointment_book_by_Patient();
			data1.setPatient_id(Integer.parseInt(userid));
			data1.setDoctor_id(data.getDoctor_id());
			data1.setBooked_date(LocalDate.now());
			data1.setBooked_time(LocalTime.now());
			data1.setAppointment_scheduled_time(data.getAppointment_scheduled_time());
			data1.setLock_version(false);
			Appointment_book_by_Patient p=this.repo.save(data1);
    		Patient_Details_To_Admin p2=this.feign.getPatientById(Integer.parseInt(userid));
    		NotificationEvent event=new NotificationEvent();
    		event.setAppointment_scheduled(data.getAppointment_scheduled_time());
    		event.setBooking_date(data1.getBooked_date());
    		event.setBooking_time(data1.getBooked_time());
    		event.setPhoneNumber(p2.getPhone_number());
			this.service.TriggerKafkaProducer_To_Admin(event,Integer.parseInt(userid));
		return ResponseEntity.status(HttpStatus.CREATED).body("Congratulations!!!   Appointment Booked on "+data.getAppointment_scheduled_time());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry!You don't have permission to book any appointment!");
	}
	
	

}
