package com.practice.techclientappointment.service;

import com.practice.techclientappointment.dto.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment addAppointment(AppointmentDto appointmentDto) throws Exception;

    Appointment findAppointmentById(Long id);

    List<Appointment> findAllAppointments();

    List<Appointment> findAppointmentByTechnicianId(Long id);

    List<Appointment> findAppointmentByClientId(Long id);

    void deleteAppointmentById(Long id);

    Appointment updateAppointment(AppointmentDto appointment);
}
