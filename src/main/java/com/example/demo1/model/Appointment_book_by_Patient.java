package com.example.demo1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="Appointment_book_by_patients")
public class Appointment_book_by_Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int patient_id;
	@NotNull
	private int doctor_id;

	private LocalDate booked_date;

	private LocalTime booked_time;
	@FutureOrPresent(message="Appointment Date should not be past date")
	private LocalDateTime appointment_scheduled_time;
	private boolean lock_version;
	public Appointment_book_by_Patient(int id, int patient_id, int doctor_id, LocalDate booked_date,
			LocalTime booked_time, LocalDateTime appointment_scheduled_time, boolean lock_version) {
		super();
		this.id = id;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.booked_date = booked_date;
		this.booked_time = booked_time;
		this.appointment_scheduled_time = appointment_scheduled_time;
		this.lock_version = lock_version;
	}
	public Appointment_book_by_Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public LocalDate getBooked_date() {
		return booked_date;
	}
	public void setBooked_date(LocalDate booked_date) {
		this.booked_date = booked_date;
	}
	public LocalTime getBooked_time() {
		return booked_time;
	}
	public void setBooked_time(LocalTime booked_time) {
		this.booked_time = booked_time;
	}
	public LocalDateTime getAppointment_scheduled_time() {
		return appointment_scheduled_time;
	}
	public void setAppointment_scheduled_time(LocalDateTime appointment_scheduled_time) {
		this.appointment_scheduled_time = appointment_scheduled_time;
	}
	public boolean isLock_version() {
		return lock_version;
	}
	public void setLock_version(boolean lock_version) {
		this.lock_version = lock_version;
	}
	
	

}
