package com.practice.techclientappointment.service;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceIMPL implements IAppointmentService  {
    @Autowired
    private AppointmentRepository appointmentRepository;



    public Appointment addAppointment(Appointment appointment) {

        // add not null appointment to be saved and returned
            return appointmentRepository.save(appointment);

    }

    public Appointment findAppointmentById(Long id){

        //use Long param id to find and return an appointment
        //Improvement -- use DTO
        return returnAppointmentIfExistById(id);
    }

    public List<Appointment> findAllAppointments() {

        // return a list of appointments

        //Improvement -- use DTO
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAppointmentByTechnicianId(Long id) {

        // return a list of appointments from db filtered by technician id (Long)

        //Improvement -- use DTO
        //Improvement -- check if tech exist and find by tech instead of id
        return appointmentRepository.findByTechId(id);
    }


    public List<Appointment> findAppointmentByClientId(Long id) {

        // return a list of appointments from db filtered by client id (Long)

        return appointmentRepository.findByClientId(id);
    }
    //add validation of data
    //starter validation avec assertj qlq methode
    // annotation de validation de startup validation
    public void deleteAppointmentById(Long id) {

        //use Long param id to delete an appointment

        returnAppointmentIfExistById(id);
            appointmentRepository.deleteById(id);

    }
    public Appointment updateAppointment(Appointment appointment) {

        // take not null appointment to update and return the updated value
        returnAppointmentIfExistById(appointment.getAppointmentId());
        return  appointmentRepository.save(appointment);

    }


    private Appointment returnAppointmentIfExistById (Long id) throws RuntimeException{
        // take not null appointment to update and return the updated value
        // improve --> throw custom exception
        return appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Error : Appointment you are trying to handle does not exist ")
        );
    }
}
