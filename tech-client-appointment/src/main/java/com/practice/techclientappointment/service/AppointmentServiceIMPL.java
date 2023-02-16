package com.practice.techclientappointment.service;

import com.practice.techclientappointment.dto.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.repository.AppointmentRepository;
import com.practice.techclientappointment.repository.ClientRepository;
import com.practice.techclientappointment.repository.TechnicianRepository;
import com.practice.techclientappointment.validations.implementaions.ObjectValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Service
@Transactional
@Slf4j
public class AppointmentServiceIMPL implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TechnicianRepository technicianRepository;


    //factory design to only initiate our Validation entity once for a performance optimisation
    private final ObjectValidator validateEntity = new ObjectValidator();

    //add validation block in every methode -- with @valid
    // add not null appointment to be saved and returned
    public Appointment addAppointment(AppointmentDto appointmentDto) {


        validateEntity.validate(appointmentDto);
        Appointment appointment = appointmentDto.toEntity();

        Technician technicianFound = technicianRepository.getReferenceById(appointmentDto.getTechId());
        Client clientFound = clientRepository.getReferenceById(appointmentDto.getClientId());
        appointment.setClient(clientFound);
        appointment.setTechnician(technicianFound);

        validateEntity.validate(appointment);
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
    public Appointment updateAppointment(@Valid AppointmentDto appointmentDto) {

        validateEntity.validate(appointmentDto);
        returnAppointmentIfExistById(appointmentDto.getId());

        Appointment appointment = appointmentDto.toEntity();
        appointment.setAppointmentId(appointmentDto.getId());
        Technician technicianFound = technicianRepository.getReferenceById(appointmentDto.getTechId());
        Client clientFound = clientRepository.getReferenceById(appointmentDto.getClientId());
        appointment.setClient(clientFound);
        appointment.setTechnician(technicianFound);

        
        return appointmentRepository.save(appointment);

    }


    private Appointment returnAppointmentIfExistById(Long id) throws RuntimeException {
        assertThat(id).isNotNull();

        // improve --> throw custom exception
        return appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Error : Appointment you are trying to handle does not exist ")
        );
    }


}
