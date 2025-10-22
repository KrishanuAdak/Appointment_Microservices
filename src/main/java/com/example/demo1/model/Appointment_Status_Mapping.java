package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="appointment_status_mapping")
public class Appointment_Status_Mapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int appointment_id;
	private int doctor_id;
	private int patient_id;
	private int approval_status_id;
	private boolean lock_version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getApproval_status_id() {
		return approval_status_id;
	}
	public void setApproval_status_id(int approval_status_id) {
		this.approval_status_id = approval_status_id;
	}
	public boolean isLock_version() {
		return lock_version;
	}
	public void setLock_version(boolean lock_version) {
		this.lock_version = lock_version;
	}
	public Appointment_Status_Mapping(int id, int app_id,int doctor_id, int patient_id, int approval_status_id,
			boolean lock_version) {
		super();
		this.id = id;
		this.appointment_id=app_id;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.approval_status_id = approval_status_id;
		this.lock_version = lock_version;
	}
	public Appointment_Status_Mapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	 

}
