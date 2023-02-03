package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Slf4j
class AppointmentRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void SHOULD_SAVE_TEST_APPOINTMENTS_DATA_IN_DB(){


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
                .time(DateUtil.now())
                .client(client)
                .build();



        appointmentRepository.save(appointment);

    }



    @Test
    public void SHOULD_THROWS_EXCEPTION_WITHOUT_TECH_ASSIGNED(){


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
    public void SHOULD_THROWS_EXCEPTION_WITHOUT_CLIENT_ASSIGNED(){



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