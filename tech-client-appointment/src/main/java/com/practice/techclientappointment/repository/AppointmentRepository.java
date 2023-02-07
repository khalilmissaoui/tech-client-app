package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {



    @Query("select appointment from Appointment appointment where appointment.client = ?1")
    List<Appointment> findByClientId(Long id);


    @Query("select appointment from Appointment appointment where appointment.technician = ?1")
    List<Appointment> findByTechId(Long id);

}
