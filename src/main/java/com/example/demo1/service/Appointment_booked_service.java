package com.example.demo1.service;

import org.springframework.stereotype.Service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.example.demo1.NotificationResponse.AppointmentDetails;
import com.example.demo1.NotificationResponse.NotificationEvent;
import com.example.demo1.NotificationResponse.Patient_Details_To_Admin;
import com.example.demo1.exception.PatientNotFoundException;
import com.example.demo1.model.Appointment_Status;
import com.example.demo1.model.Appointment_Status_Approved_Rejected;
import com.example.demo1.model.Appointment_Status_Mapping;
import com.example.demo1.model.Appointment_book_by_Patient;
import com.example.demo1.model.DoctorDetailsToAppointment;
import com.example.demo1.model.PendingAppointmentView;
import com.example.demo1.model.View_appointments_by_doctor;
import com.example.demo1.openFiegn.DoctorFeign;
import com.example.demo1.openFiegn.PatientsFiegn;
import com.example.demo1.repo.AppointmentRepo;
import com.example.demo1.repo.Appointment_Status_Mapping_Repo;
import com.example.demo1.repo.Appointment_Status_Repo;
import com.example.demo1.repo.Disease_list_repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class Appointment_booked_service {
	private final Logger logger=LoggerFactory.getLogger(Appointment_booked_service.class);
	
	private static final String TOPIC = "appointment-booked-by-patients";
	
	@Autowired(required=true)
	private KafkaTemplate<String, Object> kafkaTemplate;	   
	
//	@Autowired(required=true)
//	private KafkaTemplate<String,AppointmentDetails> template2;
	
	@Autowired
	private AppointmentRepo repo; 
	
	@Autowired
	private Disease_list_repo diseaseRepo;
	
	@Autowired
	private PatientsFiegn feign;
	
	@Autowired
	private Appointment_Status_Repo repo1;
	
	@Autowired
	private Appointment_Status_Mapping_Repo mappingRepo;
	
	@Autowired
	private DoctorFeign doctorFeign;
	
	public void TriggerKafkaProducer_To_Admin(NotificationEvent details,int id) {
		NotificationEvent notification=new NotificationEvent();
        notification.setEventType(TOPIC);
        notification.setMessage("New Appointment Has Just Booked !");
        notification.setBooking_date(LocalDate.now());
        notification.setAppointment_scheduled(details.getAppointment_scheduled());
        notification.setBooking_time(LocalTime.now());
        notification.setPhoneNumber(details.getPhoneNumber());
		this.kafkaTemplate.send(TOPIC, notification);
	}
	
	@CircuitBreaker(name="appointment_patient_service",fallbackMethod="handleCircuitBreaker")
	public Appointment_book_by_Patient saveAppointment(Appointment_book_by_Patient data,String userid,int disease_id) {
		if(data!=null) {
			Appointment_book_by_Patient data1=new Appointment_book_by_Patient();
			Appointment_Status_Mapping status_mapping=new Appointment_Status_Mapping();
			
			
//			For Saving Appointment data 
			data1.setPatient_id(Integer.parseInt(userid));
			data1.setDoctor_id(data.getDoctor_id());
			data1.setBooked_date(LocalDate.now());
			data1.setBooked_time(LocalTime.now());
			data1.setAppointment_scheduled_time(data.getAppointment_scheduled_time());
			data1.setLock_version(false);
			String disease_name=this.diseaseRepo.getDiseaseName(disease_id);
			data1.setDisease_category(disease_name);
            data1.setDisease_description(data.getDisease_description());
            int status_id=this.repo1.findIdByStatusName("pending");
            
            
            
			Appointment_book_by_Patient p=this.repo.save(data1);
			status_mapping.setAppointment_id(p.getId());
            status_mapping.setApproval_status_id(status_id);
            status_mapping.setDoctor_id(data1.getDoctor_id());
            status_mapping.setPatient_id(Integer.parseInt(userid));
            status_mapping.setLock_version(false);
            
			this.mappingRepo.save(status_mapping);
			
//			Get Data from open feign
    		Patient_Details_To_Admin p2=this.feign.getPatientById(Integer.parseInt(userid));
    		
//    		Send Message to Kafka
    		NotificationEvent event=new NotificationEvent();
    		event.setAppointment_scheduled(data.getAppointment_scheduled_time());
    		event.setBooking_date(data1.getBooked_date());
    		event.setBooking_time(data1.getBooked_time());
    		event.setPhoneNumber(p2.getPhone_number());
			this.TriggerKafkaProducer_To_Admin(event,Integer.parseInt(userid));
			Patient_Details_To_Admin patient_details=this.feign.getPatientById(Integer.parseInt(userid));
			DoctorDetailsToAppointment docDetails=this.doctorFeign.sendDoctorDetailsToAppointment(8);
			logger.info("doctor id : "+data1.getDoctor_id());
			logger.info("Patient name from feign"+patient_details.getPhone_number());
			logger.info(docDetails.getDoctor_name()+"From doctor feign ");
			
			this.notifyDoctorToApproveAppointment(p.getAppointment_scheduled_time(), disease_name,"Hello God, Dr."+docDetails.getDoctor_name()+" one new Appointment has been booked for you by "+patient_details.getPatient_name(), patient_details.getPhone_number(), p.getDisease_description(),docDetails.getPhone_number());
			return p;

		}
		
		
	   
		return null;
		
	}
	
	public void notifyDoctorToApproveAppointment(LocalDateTime scheduled_time,String disease,String details,String patient_phonenumber,String disease_description,String doctor_phone_number) {
		AppointmentDetails detailsToDoctor=new AppointmentDetails();		detailsToDoctor.setAppointment_booked_date(LocalDate.now());
		detailsToDoctor.setAppointment_booked_time(LocalTime.now());
		detailsToDoctor.setAppointment_scheduled(scheduled_time);
		detailsToDoctor.setDetails(details);
		detailsToDoctor.setPatient_phonenumber(patient_phonenumber);
		detailsToDoctor.setDisease_category(disease);
		detailsToDoctor.setDoctor_phoneNumber(doctor_phone_number);
		this.kafkaTemplate.send("check-appointment-by-doctor-approve-reject",detailsToDoctor);
	}
	
	
	public Appointment_book_by_Patient handleCircuitBreaker(Appointment_book_by_Patient data,String userid) {
		System.out.println("Circuit Breaker called");
		return null;
		
	}
	public void approveOrRejectAppointmentByDoctor(Appointment_Status_Mapping status_mapping,String status) throws Exception {
		int id=this.mappingRepo.isValidAppointment(status_mapping.getAppointment_id(),status_mapping.getDoctor_id());
		Patient_Details_To_Admin p2=this.feign.getPatientById(status_mapping.getPatient_id());

		logger.info("Appointment exist "+id);
		if(id!=0) {					
		int status_Id=this.repo1.findIdByStatusName(status);
		logger.info("Status id "+status_Id);
		this.mappingRepo.updateAppointmentByDoctorId(status_Id, status_mapping.getAppointment_id(), status_mapping.getDoctor_id());
			LocalDateTime appointment_date_time=this.repo.getAppointmentDetailsById(status_mapping.getAppointment_id(), status_mapping.getDoctor_id());
			//String message="Hey "+ p2.getPatient_name()+" Your Appointment scheduled on "+appointment_date_time +" has been "+status+"Phone :"+p2.getPhone_number();
			this.kafkaTemplate.send("update-appointment-by-doctor",status_mapping);

			
		
	
		}
	}
	
	public List<PendingAppointmentView> showAllAppointments(int doctor_id,String status){
		List<PendingAppointmentView> lists=this.repo.checkIfAnyAppointmentPending(doctor_id,status);
		if(lists.size()==0) {
			return null;
		}
		return lists;
		
	}
}
