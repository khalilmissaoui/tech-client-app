package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class AppointmentRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void saveAppointment(){


        Address address = Address.builder()
                .city("paris" )
                .houseNumber("3")
                .street("Street")
                .build();

        Client client = Client.builder()
                .type("Agency")
                .address(address )
                .build();



        Technician tech = Technician.builder()
                .firstName("tech4")
                .lastName("juan")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();


        Appointment appointment = Appointment
                .builder()
                .price("324 £")
                .technician(tech)
                .client(client)
                .build();



        appointmentRepository.save(appointment);

    }



    @Test
    public void NotSaveAppointmentIfTechNotEXIST(){


        Address address = Address.builder()
                .city("paris" )
                .houseNumber("3")
                .street("Street")
                .build();

        Client client = Client.builder()
                .type("Agency")
                .address(address )
                .build();






        Appointment appointment = Appointment
                .builder()
                .price("324 £")
                .client(client)
                .build();



        assertThrows(org.springframework.dao.DataIntegrityViolationException.class , () -> appointmentRepository.save(appointment));


    }

    @Test
    public void NotSaveAppointmentIfClientNotEXIST(){



        Technician tech = Technician.builder()
                .firstName("tech4")
                .lastName("juan")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();





        Appointment appointment = Appointment
                .builder()
                .price("324 £")
                .technician(tech)
                .build();



        assertThrows(org.springframework.dao.DataIntegrityViolationException.class , () -> appointmentRepository.save(appointment));


    }

}