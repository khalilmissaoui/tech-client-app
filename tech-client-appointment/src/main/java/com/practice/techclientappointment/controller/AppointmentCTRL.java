package com.practice.techclientappointment.controller;


import com.practice.techclientappointment.dto.dtos.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppointmentCTRL {
    //sans interface - appointmentCTRL request mmapping nom propre -
    @Autowired
    private AppointmentServiceIMPL appointmentService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<Appointment> appointmentList = appointmentService.findAllAppointments();
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentList);
        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);
    }


    @GetMapping("/by-tech/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByTechId(@PathVariable("id") String id) {
        Long parsedTechId = Long.parseLong(id);
        List<Appointment> appointmentList = appointmentService.findAppointmentByTechnicianId(parsedTechId);
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentList);

        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);

    }

    @GetMapping("/byClient/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByClientId(@PathVariable("id") String id) {
        Long parsedClientId = Long.parseLong(id);
        List<Appointment> appointmentsList = appointmentService.findAppointmentByClientId(parsedClientId);
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentsList);
        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") String id) {
        Long parsedAppointmentId = Long.parseLong(id);
        Appointment appointment = appointmentService.findAppointmentById(parsedAppointmentId);
        return new ResponseEntity<>(appointment.toDTO(), HttpStatus.OK);

    }

    //sans prefix for post / put / byid
    @PostMapping(value = {"/add-appointment"})
    public ResponseEntity<AppointmentDto> createNewAppointment(@Valid @RequestBody AppointmentDto appointment) {

        Appointment createdAppointment =
                appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(createdAppointment.toDTO(), HttpStatus.CREATED);
    }


    @PutMapping(value = {"/update-appointment"})
    public ResponseEntity<AppointmentDto> updateAppointment(@Valid @RequestBody AppointmentDto appointment) {

        Appointment createdAppointment = appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(createdAppointment.toDTO(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointmentById(@PathVariable String id) {
        Long parsedAppointmentId = Long.parseLong(id);
        appointmentService.deleteAppointmentById(parsedAppointmentId);
    }


    private List<AppointmentDto> entitiesToDTOs(List<Appointment> appointmentList) {
        return appointmentList
                .stream()
                .map(appointment -> appointment.toDTO())
                .collect(Collectors.toList());
    }

}
