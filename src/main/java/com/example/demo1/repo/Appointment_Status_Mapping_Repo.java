package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Appointment_Status_Mapping;

import jakarta.transaction.Transactional;

@Repository
public interface Appointment_Status_Mapping_Repo  extends JpaRepository<Appointment_Status_Mapping,Integer>{
	@Modifying
	@Transactional
	@Query(value="update appointment_status_mapping set approval_status_id=?1 where appointment_id=?2 and doctor_id=?3",nativeQuery=true)
	public void updateAppointmentByDoctorId(int status_Id,int appointment_id,int doctor_id);
	
	@Query(value="select id from appointment_status_mapping where appointment_id=?1 and doctor_id=?2",nativeQuery=true)
	public int isValidAppointment(int appointment_id,int doctor_id);

}
