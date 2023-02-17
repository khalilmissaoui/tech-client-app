package com.practice.techclientappointment.controller;


import com.practice.techclientappointment.dto.Mapper;
import com.practice.techclientappointment.dto.dtos.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class AppointmentCTRL {

    @Autowired
    private AppointmentServiceIMPL appointmentService;

    @Autowired
    Mapper mapper;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {

        List<Appointment> appointmentList = appointmentService.findAllAppointments();
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentList);
        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);
    }


    @GetMapping("/byTech/{id}")
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
        return new ResponseEntity<>(mapper.toDTO(appointment), HttpStatus.OK);

    }

    @PostMapping(value = {"/"})
    public ResponseEntity<AppointmentDto> createNewAppointment(@Valid @RequestBody AppointmentDto appointment) {

        Appointment createdAppointment =
                appointmentService.addAppointment(mapper.toEntity(appointment));
        return new ResponseEntity<>(mapper.toDTO(createdAppointment), HttpStatus.CREATED);
    }


    @PutMapping(value = {"/"})
    public ResponseEntity<AppointmentDto> updateAppointment(@Valid @RequestBody AppointmentDto appointmentDTO) {

        Appointment appointment = mapper.toEntity(appointmentDTO);
        appointment.setAppointmentId(appointmentDTO.getId());

        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(mapper.toDTO(updatedAppointment), HttpStatus.OK);
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
                .map(appointment -> mapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

}
