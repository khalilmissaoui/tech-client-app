package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    List<Appointment> findByClientId(Long id);

    List<Appointment> findByTechnicainId(Long id);


}
