package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.model.Appointment_book_by_Patient;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment_book_by_Patient,Integer> {
//	@Query(value="select patient_name,patient_phone_number from patient_details where patient_id=?1 and lock_version=false")
//	public Patient_Details_To_Admin getPatientDetails(int id);
	
	
}
