package com.practice.techclientappointment.controller;

import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.repository.TechnicianRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/technician")
public class TechnicianCTRL {

    @Autowired
    private TechnicianRepository technicianRepository;


    @Operation(summary = "Get all technicians")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all technicians",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Technician.class))
                            )})
    })
    @GetMapping(value = {"/all"})
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        log.info("ENDPOINT : getAllTechnicians");
        List<Technician> techsList = technicianRepository.findAll();

        return new ResponseEntity<>(techsList, HttpStatus.OK);
    }
}
