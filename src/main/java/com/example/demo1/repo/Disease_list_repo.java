package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Disease_list;

@Repository
public interface Disease_list_repo  extends JpaRepository<Disease_list,Integer>{
	
	@Query(value="select disease_category from disease_list where id=?1 and lock_version=false",nativeQuery=true)
	public String getDiseaseName(int id);

}
