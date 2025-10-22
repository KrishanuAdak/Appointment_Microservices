package com.example.demo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo1.NotificationResponse.NotificationEvent;
import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.model.Appointment_Status_Mapping;
import com.example.demo1.model.Appointment_book_by_Patient;
import com.example.demo1.model.PendingAppointmentView;
import com.example.demo1.model.View_appointments_by_doctor;
import com.example.demo1.openFiegn.PatientsFiegn;
import com.example.demo1.repo.AppointmentRepo;
import com.example.demo1.service.Appointment_booked_service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Value;


@RestController
@RequestMapping("/appointment")
public class Appointment_Controller {
	@Autowired
	private Appointment_booked_service service;
	
	@Autowired
	private AppointmentRepo repo;
	
	@Autowired(required=true)
	private PatientsFiegn feign;
	
	
	@Value("${server.port}")
	private String port;
	
	
	@GetMapping("/port")
	public String getPort() {
		return "port running on"+port;
		
	}
	
	@PostMapping("/v1/schedule")	
	public ResponseEntity<?> bookAppointment(@Valid @RequestBody Appointment_book_by_Patient data ,BindingResult result,@RequestHeader("X-User-Id") String userid,
	        @RequestHeader("X-Role") String role){		

		if(result.hasErrors()) {
			System.out.println(result.getFieldErrors());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");
		}
		if(role.equalsIgnoreCase("patient"))
		{
		
    		Patient_Details_To_Admin p2=this.feign.getPatientById(Integer.parseInt(userid));
    		NotificationEvent event=new NotificationEvent();
    		event.setAppointment_scheduled(data.getAppointment_scheduled_time());
    		event.setBooking_date(data.getBooked_date());
    		event.setBooking_time(data.getBooked_time());
    		event.setPhoneNumber(p2.getPhone_number());
			this.service.saveAppointment(data, userid,1);
			this.service.TriggerKafkaProducer_To_Admin(event,Integer.parseInt(userid));
		return ResponseEntity.status(HttpStatus.CREATED).body("Congratulations!!!   Appointment Booked on "+data.getAppointment_scheduled_time());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry!You don't have permission to book any appointment!");
	}
	@GetMapping("lists/status")  
	public ResponseEntity<?> getListOfPendingAppointments(@RequestParam(required=false) String status,@RequestHeader("X-Role") String role,@RequestHeader("X-User-Id") String userId){
	    if(role.equalsIgnoreCase("doctor")) {   
		List<PendingAppointmentView> lists=this.service.showAllAppointments(Integer.parseInt(userId),status);	
	       return ResponseEntity.status(HttpStatus.OK).body(lists);
	    }
	    return ResponseEntity.badRequest().build();
	}
	@PutMapping("update/appointment/{id}")
	public ResponseEntity<?> updateAppointmentStatusByDoctor(@RequestHeader("X-User-Id") String doctor_id,@RequestHeader("X-Role") String role,@RequestParam("status") String status,@RequestBody Appointment_Status_Mapping data, @PathVariable("id") int appointment_id ) throws NumberFormatException, Exception
	{
		
		if(role.equalsIgnoreCase("doctor")) {
			
		
		this.service.approveOrRejectAppointmentByDoctor(data, status);
				//.approveOrRejectAppointmentByDoctor(status, appointment_id, Integer.parseInt(doctor_id));
			return ResponseEntity.status(HttpStatus.OK).body("appointment updated successfully.");
		}
		
		
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission to do this action");

	}

}
