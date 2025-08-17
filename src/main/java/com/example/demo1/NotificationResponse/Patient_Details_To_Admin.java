package com.example.demo1.NotificationResponse;

public class Patient_Details_To_Admin {
	public String patient_name;
	public String phone_number;
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Patient_Details_To_Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient_Details_To_Admin(String patient_name, String phone_number) {
		super();
		this.patient_name = patient_name;
		this.phone_number = phone_number;
	}
	
	

}
