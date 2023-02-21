package com.practice.techclientappointment.controller;


import com.practice.techclientappointment.dto.Mapper;
import com.practice.techclientappointment.dto.dtos.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.service.AppointmentServiceIMPL;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    Mapper mapper;
    @Autowired
    private AppointmentServiceIMPL appointmentService;

    @Operation(summary = "Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all appointments",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class))
                            )})
    })
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        log.info("ENDPOINT : getAllAppointments");
        List<Appointment> appointmentList = appointmentService.findAllAppointments();
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentList);
        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);
    }


    @Operation(summary = "Get appointment by the technician id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of appointment by Technician ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class))
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Technician id supplied",
                    content = @Content
            ),
    })
    @GetMapping("/byTech/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByTechId(@PathVariable("id") String id) {
        log.info("ENDPOINT : getAppointmentsByTechId" + " [id] : " + id);
        Long parsedTechId = Long.parseLong(id);
        List<Appointment> appointmentList = appointmentService.findAppointmentByTechnicianId(parsedTechId);
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentList);

        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);

    }

    @Operation(summary = "Get appointment by the client id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of appointment by Client ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppointmentDto.class))
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Client id supplied",
                    content = @Content
            ),
    })
    @GetMapping("/byClient/{id}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByClientId(@PathVariable("id") String id) {
        log.info("ENDPOINT : getAppointmentsByClientId" + " [id] : " + id);

        Long parsedClientId = Long.parseLong(id);
        List<Appointment> appointmentsList = appointmentService.findAppointmentByClientId(parsedClientId);
        List<AppointmentDto> appointmentDtoList = entitiesToDTOs(appointmentsList);
        return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);

    }

    @Operation(summary = "Get appointment by the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the appointment",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppointmentDto.class)
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse
                    (
                            responseCode = "404",
                            description = "Appointment does not exist",
                            content = @Content
                    )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(
            @Parameter(description = "id of appointment to be searched")
            @PathVariable("id") String id) {

        log.info("ENDPOINT : getAppointmentById" + " [id] : " + id);

        Long parsedAppointmentId = Long.parseLong(id);
        Appointment appointment = appointmentService.findAppointmentById(parsedAppointmentId);
        return new ResponseEntity<>(mapper.toDTO(appointment), HttpStatus.OK);

    }

    @Operation(summary = "Create new appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppointmentDto.class)
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid appointment object",
                    content = @Content
            ),

    })
    @PostMapping(value = {"/"})
    public ResponseEntity<AppointmentDto> createNewAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {

        log.info("ENDPOINT : createNewAppointment" + " [AppointmentDto] : " + appointmentDto.toString());

        Appointment createdAppointment =
                appointmentService.addAppointment(mapper.toEntity(appointmentDto));
        return new ResponseEntity<>(mapper.toDTO(createdAppointment), HttpStatus.CREATED);
    }


    @Operation(summary = "Update an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppointmentDto.class)
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid appointment object",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Appointment does not exist",
                    content = @Content
            ),
    })
    @PutMapping(value = {"/"})
    public ResponseEntity<AppointmentDto> updateAppointment(@Valid @RequestBody AppointmentDto appointmentDTO) {

        log.info("ENDPOINT : updateAppointment" + " [AppointmentDto] : " + appointmentDTO.toString());


        Appointment appointment = mapper.toEntity(appointmentDTO);
        appointment.setAppointmentId(appointmentDTO.getId());

        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(mapper.toDTO(updatedAppointment), HttpStatus.OK);
    }


    @Operation(summary = "Delete appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appointment deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppointmentDto.class)
                            )}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse
                    (responseCode = "404",
                            description = "Appointment does not exist",
                            content = @Content
                    )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointmentById(@PathVariable String id) {
        log.info("ENDPOINT : deleteAppointmentById" + " [id] : " + id);

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
