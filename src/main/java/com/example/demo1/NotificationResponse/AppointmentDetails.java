package com.example.demo1.NotificationResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDetails {
	private String details;
	private String patient_phonenumber;
	private String disease_description;
	private LocalDate appointment_booked_date;
	private LocalTime appointment_booked_time;
	private LocalDateTime appointment_scheduled;
	private String doctor_phoneNumber;
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getPatient_phonenumber() {
		return patient_phonenumber;
	}
	public void setPatient_phonenumber(String patient_phonenumber) {
		this.patient_phonenumber = patient_phonenumber;
	}
	public String getDisease_category() {
		return disease_description;
	}
	public void setDisease_category(String disease_description) {
		this.disease_description = disease_description;
	}
	public LocalDate getAppointment_booked_date() {
		return appointment_booked_date;
	}
	public void setAppointment_booked_date(LocalDate appointment_booked_date) {
		this.appointment_booked_date = appointment_booked_date;
	}
	public LocalTime getAppointment_booked_time() {
		return appointment_booked_time;
	}
	public void setAppointment_booked_time(LocalTime appointment_booked_time) {
		this.appointment_booked_time = appointment_booked_time;
	}
	public LocalDateTime getAppointment_scheduled() {
		return appointment_scheduled;
	}
	public void setAppointment_scheduled(LocalDateTime appointment_scheduled) {
		this.appointment_scheduled = appointment_scheduled;
	}
	
	public String getDisease_description() {
		return disease_description;
	}
	public void setDisease_description(String disease_description) {
		this.disease_description = disease_description;
	}
	public String getDoctor_phoneNumber() {
		return doctor_phoneNumber;
	}
	public void setDoctor_phoneNumber(String doctor_phoneNumber) {
		this.doctor_phoneNumber = doctor_phoneNumber;
	}
	
	public AppointmentDetails(String details, String patient_phonenumber, String disease_description,
			LocalDate appointment_booked_date, LocalTime appointment_booked_time, LocalDateTime appointment_scheduled,
			String doctor_phoneNumber) {
		super();
		this.details = details;
		this.patient_phonenumber = patient_phonenumber;
		this.disease_description = disease_description;
		this.appointment_booked_date = appointment_booked_date;
		this.appointment_booked_time = appointment_booked_time;
		this.appointment_scheduled = appointment_scheduled;
		this.doctor_phoneNumber = doctor_phoneNumber;
	}
	public AppointmentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
