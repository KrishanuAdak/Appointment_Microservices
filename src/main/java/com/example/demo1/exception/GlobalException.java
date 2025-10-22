package com.example.demo1.exception;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler
	public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException ex){
		return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Patient Not Found");
		
	}

}
