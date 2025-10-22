package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="disease_list")
public class Disease_list {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String disease_category;
    private boolean lock_version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDisease_category() {
		return disease_category;
	}
	public void setDisease_category(String disease_category) {
		this.disease_category = disease_category;
	}
	public boolean isLock_version() {
		return lock_version;
	}
	public void setLock_version(boolean lock_version) {
		this.lock_version = lock_version;
	}
	public Disease_list(int id, String disease_category, boolean lock_version) {
		super();
		this.id = id;
		this.disease_category = disease_category;
		this.lock_version = lock_version;
	}
	public Disease_list() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
