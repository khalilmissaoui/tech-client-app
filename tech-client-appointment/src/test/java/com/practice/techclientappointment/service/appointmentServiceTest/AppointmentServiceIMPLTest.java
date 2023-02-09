package com.practice.techclientappointment.service.appointmentServiceTest;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.repository.AppointmentRepository;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

class AppointmentServiceIMPLTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceIMPL appointmentService;

    @TestConfiguration
    static class AppointmentServiceContextConfiguration {
        @Bean
        public AppointmentServiceIMPL appointmentService() {
            return new AppointmentServiceIMPL();
        }
    }


    @Test
    void SHOULD_RETURN_ALL_APPOINTMENTS() {

        //GIVEN
        Client client = Client.builder()
                .type("Agency x")
                .build();
        Technician tech = Technician.builder()
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();
        Appointment appointment1 = Appointment.builder().price("3223 £").client(client).technician(tech).time(new Date()).build();
        Appointment appointment2 = Appointment.builder().price("323 £").time(new Date()).client(client).technician(tech).build();
        List<Appointment> appointmentList = Arrays.asList(appointment1, appointment2);

        //WHEN
        given(appointmentRepository.findAll()).willReturn(appointmentList);

        //THEN
        assertThat(appointmentService.findAllAppointments())
                .hasSize(2)
                .contains(appointment1, appointment2);

    }

    @Test
    void SHOULD_RETURN_ALL_APPOINTMENTS_BY_TECHNICIAN_ID() {

        //GIVEN
        Long ID = 1L ;
        Client client = Client.builder()
                .type("Agency x")
                .build();
        Technician tech = Technician.builder()
                .techId(ID)
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();
        Technician tech2 = Technician.builder()
                .techId(ID)
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();
        Appointment appointment1 = Appointment.builder().price("3223 £").client(client).technician(tech).build();
        Appointment appointment2 = Appointment.builder().price("323 £").client(client).technician(tech2).build();
        List<Appointment> appointmentList = Arrays.asList(appointment1, appointment2);

        //WHEN
        given(appointmentRepository.findByTechId(ID)).willReturn(appointmentList);
        List<Appointment> appointments = appointmentService.findAppointmentByTechnicianId(ID);

        //THEN
        assertThat(appointments).hasSize(2).contains(appointment1,appointment2);
        appointments.forEach(
                appointment -> assertThat(appointment.getTechnician().getTechId() == ID).isTrue()
        );



    }

    @Test
    void SHOULD_RETURN_ALL_APPOINTMENTS_BY_CLIENT_ID() {

        //GIVEN
        Long ID = 1L ;
        Client client = Client.builder()
                .clientId(ID)
                .type("Agency x")
                .build();
        Client client2 = Client.builder()
                .clientId(ID)
                .type("Agency x")
                .build();
        Technician tech = Technician.builder()
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();
        Appointment appointment1 = Appointment.builder().price("3223 £").client(client).technician(tech).build();
        Appointment appointment2 = Appointment.builder().price("323 £").client(client2).technician(tech).build();
        List<Appointment> appointmentList = Arrays.asList(appointment1, appointment2);

        //WHEN
        given(appointmentRepository.findByClientId(ID)).willReturn(appointmentList);
        List<Appointment> appointments = appointmentService.findAppointmentByClientId(ID);

        //THEN
        assertThat(appointments).hasSize(2).contains(appointment1,appointment2);
        appointments.forEach(
                appointment -> assertThat(appointment.getClient().getClientId() == ID).isTrue()
        );



    }

    @Test
    void SHOULD_RETURN_FALSE_AND_NOT_EXECUTE_DELETEBYID_IF_CANNOT_FIND_APPOINTMENT_BY_ID() {

        //GIVEN
        Long ID = 2L;
        Client client = Client.builder()
                .clientId(ID)
                .type("Agency x")
                .build();
        Technician tech = Technician.builder()
                .techId(ID)
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();
        Appointment appointment = Appointment.builder().appointmentId(ID).price("323 £").time(new Date()).client(client).technician(tech).build();

        //WHEN
        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.empty());
        //THEN
        assertThrows(RuntimeException.class , ()-> appointmentService.deleteAppointmentById(appointment.getAppointmentId()));
        verify(appointmentRepository , never()).deleteById(1L);




    }



}