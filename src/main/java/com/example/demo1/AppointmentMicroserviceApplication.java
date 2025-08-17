package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.cloud.openfeign.EnableFeignClients;
import 
org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = "com.example.demo1.openFiegn")
@EnableDiscoveryClient


public class AppointmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentMicroserviceApplication.class, args);
	}

}
