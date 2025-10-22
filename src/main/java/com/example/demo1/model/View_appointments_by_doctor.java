package com.example.demo1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class View_appointments_by_doctor {
	private String disease_description;
	private LocalDateTime appointment_scheduled;
	private LocalDate appointment_booked_date;
	private LocalTime appointment_booked_time;
	public String getDisease_description() {
		return disease_description;
	}
	public void setDisease_description(String disease_description) {
		this.disease_description = disease_description;
	}
	public LocalDateTime getAppointment_scheduled() {
		return appointment_scheduled;
	}
	public void setAppointment_scheduled(LocalDateTime appointment_scheduled) {
		this.appointment_scheduled = appointment_scheduled;
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
	public View_appointments_by_doctor(String disease_description, LocalDateTime appointment_scheduled,
			LocalDate appointment_booked_date, LocalTime appointment_booked_time) {
		super();
		this.disease_description = disease_description;
		this.appointment_scheduled = appointment_scheduled;
		this.appointment_booked_date = appointment_booked_date;
		this.appointment_booked_time = appointment_booked_time;
	}
	public View_appointments_by_doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
