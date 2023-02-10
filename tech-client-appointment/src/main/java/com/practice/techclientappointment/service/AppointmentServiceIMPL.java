package com.practice.techclientappointment.service;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Service
@Transactional
public class AppointmentServiceIMPL implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    //add validation block in every methode -- with @valid
    // add not null appointment to be saved and returned
    public Appointment addAppointment(@Valid Appointment appointment) {

        return appointmentRepository.save(appointment);

    }

    //use Long param id to find and return an appointment
    public Appointment findAppointmentById(Long id) {


        assertThat(id).isNotNull();

        //Improvement -- use DTO
        return returnAppointmentIfExistById(id);
    }

    // return a list of appointments
    public List<Appointment> findAllAppointments() {

        //Improvement -- use DTO
        return appointmentRepository.findAll();
    }


    // return a list of appointments from db filtered by technician id (Long)
    public List<Appointment> findAppointmentByTechnicianId(Long id) {


        assertThat(id).isNotNull();

        //Improvement -- use DTO
        //Improvement -- check if tech exist and find by tech instead of id
        return appointmentRepository.findByTechId(id);
    }


    // return a list of appointments from db filtered by client id (Long)
    public List<Appointment> findAppointmentByClientId(Long id) {
        assertThat(id).isNotNull();


        return appointmentRepository.findByClientId(id);
    }

    //use Long param id to delete an appointment
    public void deleteAppointmentById(Long id) {

        assertThat(id).isNotNull();

        returnAppointmentIfExistById(id);
        appointmentRepository.deleteById(id);

    }

    // take not null appointment to update and return the updated value
    public Appointment updateAppointment(@Valid Appointment appointment) {

        returnAppointmentIfExistById(appointment.getAppointmentId());
        return appointmentRepository.save(appointment);

    }


    // take not null appointment to update and return the updated value
    private Appointment returnAppointmentIfExistById(Long id) throws RuntimeException {
        assertThat(id).isNotNull();

        // improve --> throw custom exception
        return appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Error : Appointment you are trying to handle does not exist ")
        );
    }
}
