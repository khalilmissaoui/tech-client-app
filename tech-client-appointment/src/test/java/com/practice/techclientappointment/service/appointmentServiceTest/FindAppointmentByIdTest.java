package com.practice.techclientappointment.service.appointmentServiceTest;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.repository.AppointmentRepository;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

public class FindAppointmentByIdTest {

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
    void SHOULD_FIND_AND_RETURN_APPOINTMENT_BY_ID(){

        //GIVEN
        Long ID = 2L;
        Appointment appointment = Appointment.builder().appointmentId(ID).price("323 £").time(new Date()).build();

        //WHEN
        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.ofNullable(appointment) );

        //THEN
        Appointment retriedAppointment = appointmentService.findAppointmentById(ID);
        assertThat(retriedAppointment.getAppointmentId()).isEqualTo(appointment.getAppointmentId());
    }


    @Test
    void SHOULD_THROWS_ASSERTION_ERROR_WHEN_ID_IS_NULL(){

        //THEN
        assertThrows(AssertionError.class , ()-> appointmentService.findAppointmentById(null));


    }

    @Test
    void SHOULD_CALL_REPOSITORY_WITH_SAME_PASSED_ID(){

        //GIVEN
        Long ID = 2L;
        Appointment appointment = Appointment.builder().appointmentId(ID).price("323 £").time(new Date()).build();

        //WHEN
        given(appointmentRepository.findById(appointment.getAppointmentId())).willReturn(Optional.ofNullable(appointment) );

        appointmentService.findAppointmentById(ID);

        //THEN
        ArgumentCaptor<Long> appointmentArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(appointmentRepository)
                .findById(appointmentArgumentCaptor.capture());

        Long capturedAppointment = appointmentArgumentCaptor.getValue();
        assertThat(capturedAppointment).isEqualTo(ID);

    }


}
