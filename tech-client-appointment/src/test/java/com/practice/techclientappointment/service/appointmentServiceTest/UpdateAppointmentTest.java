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

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

public class UpdateAppointmentTest {
    //update name to methode name
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
    void SHOULD_FIND_APPOINTMENT_AND_UPDATE_IT_BY_ID() {
        //GIVEN
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

        //WHEN
        given(appointmentRepository.findById(appointment.getAppointmentId()))
                .willReturn(Optional.of(appointment));
        given(appointmentRepository.save(appointment))
                .willReturn(appointment);

        //THEN
        assertThat(appointmentService.updateAppointment(appointment))
                .isEqualTo(appointment);

    }

    @Test
    void SHOULD_THROW_RT_EXCEPTION_WHEN_NOT_FIND_APPOINTMENT_BY_ID() {
        //GIVEN
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

        //WHEN
        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.empty());

        //THEN
        assertThrows(RuntimeException.class, () -> appointmentService.updateAppointment(appointment));

    }

    @Test
    void SHOULD_THROW_EXCEPTION_INVALID_APPOINTMENT_PARAM() {

        //GIVEN
        Client client = Client.builder()
                .clientId(6L)
                .type("Agency x TYPE")
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
        //appointment is missing price
        Appointment appointment = Appointment.builder().appointmentId(1L).time(new Date()).client(client).technician(tech).build();

        //THEN
        assertThrows(RuntimeException.class, () -> appointmentService.updateAppointment(appointment));


    }
}
