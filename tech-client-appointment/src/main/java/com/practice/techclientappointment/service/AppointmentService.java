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



    public Appointment add_appointment(Appointment appointment) {

        return appointmentRepository.save(appointment);
    }


    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByTechnician(Long id) {
        return appointmentRepository.findByTechnicainId(id);
    }


    public List<Appointment> findByClient(Long id) {
        return appointmentRepository.findByClientId(id);
    }

    public void delete_appointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public Appointment update_appointment (Appointment appointment) {
        return  appointmentRepository.save(appointment);
    }
}
