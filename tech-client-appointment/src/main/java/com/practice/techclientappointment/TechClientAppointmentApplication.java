package com.practice.techclientappointment;

import com.practice.techclientappointment.entity.Agency;
import com.practice.techclientappointment.repository.AgencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.practice.techclientappointment.repository" })
@EntityScan(basePackages = { "com.practice.techclientappointment.entity" })
public class TechClientAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechClientAppointmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(AgencyRepository agencyRepository) {
		return args -> {
			Agency agency1 = Agency.builder()
					.name("Agence 1")
					.localisation("Paris sud")
					.build();

			Agency agency2 = Agency.builder()
					.name("Agence 2")
					.localisation("Paris nord")
					.build();



			agencyRepository.save(agency1);
			agencyRepository.save(agency2);
		};
	}
}
