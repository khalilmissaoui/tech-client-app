package com.practice.techclientappointment;

import com.practice.techclientappointment.entity.Address;
import com.practice.techclientappointment.entity.Agency;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.repository.AgencyRepository;
import com.practice.techclientappointment.repository.ClientRepository;
import com.practice.techclientappointment.repository.TechnicianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackages = {"com.practice.techclientappointment.repository"})
@EntityScan(basePackages = {"com.practice.techclientappointment.entity"})
public class TechClientAppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechClientAppointmentApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner loadData(AgencyRepository agencyRepository, TechnicianRepository technicianRepository
            , ClientRepository clientRepository) {
        return args -> {


            // BUILD AGENCIES
            Agency agency1 = Agency.builder()
                    .name("Agence 1")
                    .localisation("Paris sud")
                    .build();

            Agency agency2 = Agency.builder()
                    .name("Agence 2")
                    .localisation("Paris nord")
                    .build();


            // BUILD TECHNICIANS
            Technician tech1 = Technician.builder()
                    .firstName("rami")
                    .lastName("petit")
                    .phoneNumber("142497733")
                    .personalPhoneNumber("1772398842")
                    .Zone("C")
                    .speciality("electrique")
                    .isAvailable(true)
                    .build();

            Technician tech2 = Technician.builder()
                    .firstName("tech2")
                    .lastName("last2")
                    .phoneNumber("234239999")
                    .personalPhoneNumber("232998842")
                    .Zone("D")
                    .speciality("batiment")
                    .isAvailable(false)
                    .build();


            // BUILD CLIENTS
            int CREATED_CLIENTS = 5;


            for (int i = 0; i < CREATED_CLIENTS; i++) {
                Address address = Address.builder()
                        .city("paris " + i)
                        .houseNumber(Integer.toString(i))
                        .street("Street " + i)
                        .build();

                Client client = Client.builder()
                        .type("Agency" + i + "TYPE")
                        .address(address)
                        .build();
                clientRepository.save(client);
            }


            // SAVE AGENCIES ENTITIES
            agencyRepository.save(agency1);
            agencyRepository.save(agency2);


            // SAVE TECHNICIANS
            technicianRepository.save(tech1);
            technicianRepository.save(tech2);
        };
    }
}
