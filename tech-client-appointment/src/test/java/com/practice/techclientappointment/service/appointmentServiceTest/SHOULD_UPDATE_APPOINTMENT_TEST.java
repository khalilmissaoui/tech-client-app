package com.practice.techclientappointment.service.appointmentServiceTest;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.repository.AppointmentRepository;
import com.practice.techclientappointment.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SHOULD_UPDATE_APPOINTMENT_TEST {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @TestConfiguration
    static class AppointmentServiceContextConfiguration {
        @Bean
        public AppointmentService appointmentService() {
            return new AppointmentService();
        }
    }



    @Test
    void SHOULD_FIND_APPOINTMENT_BY_ID_AND_UPDATE_APPOINTMENT() {
        Client client = Client.builder()
                .clientId(6L)
                .type("Agency x")
                .build();

        Technician tech = Technician.builder()
                .techId(4L)
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();

        Appointment appointment = Appointment.builder().appointmentId(2L).price("323 £").time(new Date()).client(client).technician(tech).build();



        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.of(appointment));
        given(appointmentRepository.save(appointment)).willReturn(appointment);
        assertThat(appointmentService.updateAppointment(appointment)).isEqualTo(appointment);

    }

    @Test
    void SHOULD_THROW_RT_EXCEPTION_WHEN_NOT_FIND_APPOINTMENT_BY_ID() {
        Client client = Client.builder()
                .clientId(6L)
                .type("Agency x")
                .build();

        Technician tech = Technician.builder()
                .techId(4L)
                .firstName("tech4")
                .lastName("achref")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();

        Appointment appointment = Appointment.builder().appointmentId(2L).price("323 £").time(new Date()).client(client).technician(tech).build();



        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.empty());
        assertThrows(RuntimeException.class , ()-> appointmentService.updateAppointment(appointment));

    }
}
