package com.example.demo1.service;

import org.springframework.stereotype.Service;

import com.example.demo1.NotificationResponse.NotificationEvent;
import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.model.AppointmentDetails;
import com.example.demo1.model.Appointment_book_by_Patient;
import com.example.demo1.repo.AppointmentRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class Appointment_booked_service {
	
	private static final String TOPIC = "appointment-booked-by-patients";
	@Autowired
	private KafkaTemplate<String, NotificationEvent> kafkaTemplate;	    
	@Autowired
	private AppointmentRepo repo; 
	
	public void TriggerKafkaProducer_To_Admin(NotificationEvent details,int id) {
		NotificationEvent notification=new NotificationEvent();
        notification.setEventType(TOPIC);
        notification.setMessage("New Appointment Has Just Booked !");
        notification.setBooking_date(details.getBooking_date());
        notification.setAppointment_scheduled(details.getAppointment_scheduled());
        notification.setBooking_time(details.getBooking_time());
        notification.setPhoneNumber(details.getPhoneNumber());
		this.kafkaTemplate.send(TOPIC, notification);
	}
	
	

}
