package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("select appointment from Appointment appointment where appointment.client.clientId = ?1")
    List<Appointment> findByClientId(Long id);


    @Query("select appointment from Appointment appointment where appointment.technician.techId = ?1")
    List<Appointment> findByTechId(Long id);

}
