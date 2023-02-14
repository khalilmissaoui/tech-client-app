package com.practice.techclientappointment.controller;


import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentServiceIMPL appointmentService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointmentList = appointmentService.findAllAppointments();
        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @GetMapping("/{id}/by-tech")
    public ResponseEntity<List<Appointment>> getAppointmentByTechId(@PathVariable("id") String id) {
        Long parsedTechId = Long.parseLong(id);
        List<Appointment> appointmentsList = appointmentService.findAppointmentByTechnicianId(parsedTechId);
        return new ResponseEntity<>(appointmentsList, HttpStatus.OK);

    }

    @GetMapping("/{id}/by-client")
    public ResponseEntity<List<Appointment>> getAppointmentByClientId(@PathVariable("id") String id) {
        Long parsedClientId = Long.parseLong(id);
        List<Appointment> appointmentsList = appointmentService.findAppointmentByClientId(parsedClientId);
        return new ResponseEntity<>(appointmentsList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") String id) {
        Long parsedAppointmentId = Long.parseLong(id);
        Appointment appointment = appointmentService.findAppointmentById(parsedAppointmentId);
        return new ResponseEntity<>(appointment, HttpStatus.OK);

    }


    @PostMapping(value = {"/add-appointment"})
    public ResponseEntity<Appointment> createNewAppointment(@Valid @RequestBody Appointment appointment) {

        Appointment createdAppointment = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/update-appointment"})
    public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment) {

        Appointment createdAppointment = appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointmentById(@PathVariable String id) {
        Long parsedAppointmentId = Long.parseLong(id);
        appointmentService.deleteAppointmentById(parsedAppointmentId);
    }

}
