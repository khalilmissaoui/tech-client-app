package com.practice.techclientappointment.service.appointmentServiceTest;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

public class AddAppointmentTest {
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

    // Entities validator
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void SHOULD_SAVE_AND_RETURN_APPOINTMENT() {

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
        Appointment appointment = Appointment.builder().appointmentId(1L).price("1325 $").time(new Date()).client(client).technician(tech).build();


        //WHEN
        given(appointmentRepository.save(appointment)).willReturn(appointment);

        //THEN
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        assertThat(violations.size()).isEqualTo(0);
        assertThat(appointmentService.addAppointment(appointment)).isEqualTo(appointment);

    }


    @Test
    void SHOULD_VALIDATE_APPOINTMENT_AND_DETECT_NOTVALID_FIELDS() {

        //GIVEN
        Appointment appointment2 = Appointment.builder().price("").time(new Date()).build();

        //THEN
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment2);

        // 4 detected NOT valid fields :  client id / tech id / price length less than 2 / price blank
        assertThat(violations.size()).isEqualTo(4);
    }

    @Test
    void SHOULD_CALL_REPOSITORY_WITH_SAME_SERVICE_OBJECT() {

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
        Appointment appointment = Appointment.builder().appointmentId(1L).price("1325 $").time(new Date()).client(client).technician(tech).build();

        //WHEN
        appointmentService.addAppointment(appointment);

        //THEN
        // object validator
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);
        assertThat(violations.size()).isEqualTo(0);
        // repository spy
        ArgumentCaptor<Appointment> appointmentArgumentCaptor =
                ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository)
                .save(appointmentArgumentCaptor.capture());
        Appointment capturedAppointment = appointmentArgumentCaptor.getValue();
        assertThat(capturedAppointment).isEqualTo(appointment);

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
        assertThrows(RuntimeException.class, () -> appointmentService.addAppointment(appointment));


    }

}
