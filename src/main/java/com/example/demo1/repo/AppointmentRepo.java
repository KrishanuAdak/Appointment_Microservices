package com.example.demo1.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.model.Appointment_Status_Approved_Rejected;
import com.example.demo1.model.Appointment_book_by_Patient;
import com.example.demo1.model.PendingAppointmentView;
import com.example.demo1.model.View_appointments_by_doctor;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment_book_by_Patient,Integer> {
	
	
	@Query(value = """
	        SELECT 
	            a.disease_description AS diseaseDescription,
	            a.appointment_scheduled_time AS appointmentScheduledTime,
	            a.booked_date AS bookedDate,
	            a.booked_time AS bookedTime
	        FROM appointment_book_by_patients a
	        INNER JOIN appointment_status_mapping m ON a.id = m.appointment_id
	        INNER JOIN appointment_status s ON s.id = m.approval_status_id
	        WHERE a.doctor_id = ?1 AND (?2 is null or s.status_name=?2)
	        """, nativeQuery = true)
	    List<PendingAppointmentView> checkIfAnyAppointmentPending(int doctorId,String status);
	
	
  @Query(value="select appointment_scheduled_time from appointment_book_by_patients where id=?1 and doctor_id=?2",nativeQuery=true)
  public LocalDateTime getAppointmentDetailsById(int appointment_id,int doctor_id);
  
}
	
	
	
	
	
	
	
//	@Query(value="select patient_name,patient_phone_number from patient_details where patient_id=?1 and lock_version=false")
//	public Patient_Details_To_Admin getPatientDetails(int id);
	
	
	
//	@Query(value="select a.disease_description,a.appointment_scheduled_time,a.booked_date,a.booked_time from appointment_book_by_patients a\n"
//			+ " inner join appointment_status_mapping m\n"
//			+ " on a.doctor_id=m.doctor_id\n"
//			+ "inner join appointment_status s\n"
//			+ "on s.id=m.approval_status_id where a.doctor_id=?1 and s.status_name='pending'",nativeQuery=true)
//	public List<View_appointments_by_doctor> checkIfAnyAppointmentPending(int doctor_id); 
//	
		
	
	

