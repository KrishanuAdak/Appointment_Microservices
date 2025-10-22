package com.example.demo1.kafkaConfig;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {	
	@Bean
	public NewTopic appointmentTopic() {	
		    return TopicBuilder.name("appointment-booked-by-patients")
		            .partitions(3)
		            .replicas(1) // Must not exceed number of brokers
		            .build();
		

	}
	
//	@Lazy
	@Bean
	public NewTopic pushAppointmentToNotifyDoctoe() {
		return TopicBuilder.name("check-appointment-by-doctor-approve-reject")
				.partitions(3)
				.replicas(1)
				.build();
	}


}
