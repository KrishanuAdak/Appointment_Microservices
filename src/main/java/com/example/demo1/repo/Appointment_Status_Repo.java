package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Appointment_Status;
@Repository
public interface Appointment_Status_Repo extends JpaRepository<Appointment_Status,Integer>{
	
	@Query(value = "SELECT id FROM appointment_status WHERE status_name=?1 and is_accessed_by=true", nativeQuery = true)
	public int findIdByStatusName(String  status);

}
