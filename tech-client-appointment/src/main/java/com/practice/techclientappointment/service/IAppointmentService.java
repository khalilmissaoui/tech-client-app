package com.practice.techclientappointment.service;

import com.practice.techclientappointment.entity.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment addAppointment(Appointment appointment);
    Appointment findAppointmentById(Long id);

    List<Appointment> findAllAppointments();

    List<Appointment> findAppointmentByTechnicianId(Long id);

    List<Appointment> findAppointmentByClientId(Long id);

    Boolean deleteAppointmentById(Long id);


    Appointment updateAppointment(Appointment appointment);
}
