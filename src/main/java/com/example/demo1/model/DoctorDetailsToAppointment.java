package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDetailsToAppointment {
	
    @JsonProperty("doctor_Name")
	public String doctor_name;
    @JsonProperty("phone_Number")
	public String phone_number;
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public DoctorDetailsToAppointment(String doctor_name, String phone_number) {
		super();
		this.doctor_name = doctor_name;
		this.phone_number = phone_number;
	}
	public DoctorDetailsToAppointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
