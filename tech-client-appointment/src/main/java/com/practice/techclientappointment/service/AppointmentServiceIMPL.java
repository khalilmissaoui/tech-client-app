package com.practice.techclientappointment.service;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.exceptions.NotFoundException;
import com.practice.techclientappointment.repository.AppointmentRepository;
import com.practice.techclientappointment.validations.implementaions.ObjectValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Service
@Transactional
@Slf4j
public class AppointmentServiceIMPL implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    //factory design to only initiate our Validation entity once for a performance optimisation
    private final ObjectValidator validateEntity = new ObjectValidator();


    public Appointment addAppointment(Appointment appointment) {


        validateEntity.validate(appointment);
        return appointmentRepository.save(appointment);

    }


    //use Long param id to find and return an appointment
    public Appointment findAppointmentById(Long id) {


        assertThat(id).isNotNull();

        return returnAppointmentIfExistById(id);
    }

    // return a list of appointments
    public List<Appointment> findAllAppointments() {

        return appointmentRepository.findAll();
    }


    public List<Appointment> findAllAppointmentsListByPageAndListSize(
            int page, int size, String sortProperty) {

        assertThat(page).isNotNull();
        assertThat(size).isNotNull();
        //even it is blank , the query still work pretty fine without sorting
        assertThat(sortProperty).isNotNull();


        PageRequest pageReq
                = PageRequest.of(page, size, Sort.by(sortProperty));

        Page<Appointment> appointments = appointmentRepository
                .findAll(pageReq);
        return appointments.getContent();
    }


    // return a list of appointments from db filtered by technician id (Long)
    public List<Appointment> findAppointmentByTechnicianId(Long id) {

        assertThat(id).isNotNull();
        //Improvement -- check if tech exist and find by tech instead of id
        return appointmentRepository.findByTechId(id);
    }


    // return a list of appointments from db filtered by client id (Long)
    public List<Appointment> findAppointmentByClientId(Long id) {
        assertThat(id).isNotNull();

        // same chekc as techid
        return appointmentRepository.findByClientId(id);
    }

    //use Long param id to delete an appointment
    public void deleteAppointmentById(Long id) {

        assertThat(id).isNotNull();

        returnAppointmentIfExistById(id);
        appointmentRepository.deleteById(id);

    }

    // take not null appointment to update and return the updated value
    public Appointment updateAppointment(Appointment appointment) {

        validateEntity.validate(appointment);
        returnAppointmentIfExistById(appointment.getAppointmentId());

        return appointmentRepository.save(appointment);

    }


    private Appointment returnAppointmentIfExistById(Long id) {
        assertThat(id).isNotNull();
        return appointmentRepository.findById(id).orElseThrow(
                NotFoundException::new
        );
    }


}
