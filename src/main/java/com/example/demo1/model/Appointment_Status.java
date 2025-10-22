package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Appointment_Status")
public class Appointment_Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String status_name;
	private boolean lock_version;
	private boolean isAccessedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public boolean isLock_version() {
		return lock_version;
	}
	public void setLock_version(boolean lock_version) {
		this.lock_version = lock_version;
	}
	
	
	public boolean isAccessedBy() {
		return isAccessedBy;
	}
	public void setAccessedBy(boolean isAccessedBy) {
		this.isAccessedBy = isAccessedBy;
	}
	public Appointment_Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appointment_Status(int id, String status_name, boolean lock_version,boolean isAccessedBy) {
		super();
		this.id = id;
		this.status_name = status_name;
		this.lock_version = lock_version;
		this.isAccessedBy=isAccessedBy;
	}
	

}
