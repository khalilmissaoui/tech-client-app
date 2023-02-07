package com.practice.techclientappointment.service;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentService  {
    @Autowired
    private AppointmentRepository appointmentRepository;



    public Appointment addAppointment(Appointment appointment) {

        try {
            return appointmentRepository.save(appointment);

        }catch (Exception ex){
            // IMPROVE USE CUSTOM EXCEPTION
            throw new RuntimeException("APPOINTMENT NOT SAVED");
        }
    }

    public Appointment findAppointmentById(Long id){

        //Improvement -- use DTO
        return returnAppointmentIfExistById(id);
    }

    public List<Appointment> findAllAppointments() {

        //Improvement -- use DTO
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAppointmentByTechnicianId(Long id) {

        //Improvement -- use DTO
        //Improvement -- check if tech exist and find by tech instead of id
        return appointmentRepository.findByTechId(id);
    }


    public List<Appointment> findAppointmentByClientId(Long id) {

        //Improvement -- check if client exist and find by client instead of id
        return appointmentRepository.findByClientId(id);
    }

    public Boolean deleteAppointmentById(Long id) {

        try{
            returnAppointmentIfExistById(id);

            appointmentRepository.deleteById(id);

            return true;
        }
        catch (Exception ex){
            // improve --> throw custom exception
            return false;
        }

    }

    public Appointment updateAppointment(Appointment appointment) {

        returnAppointmentIfExistById(appointment.getAppointmentId());

        return  appointmentRepository.save(appointment);
    }


    private Appointment returnAppointmentIfExistById (Long id) {
        // improve --> throw custom exception
        return appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Error : Appointment you are trying to handle does not exist ")
        );
    }
}
