package com.practice.techclientappointment.controller;

import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.repository.ClientRepository;
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
@RequestMapping("/client")
public class ClientCTRL {

    @Autowired
    private ClientRepository clientRepository;


    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all clients",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Client.class))
                            )})
    })
    @GetMapping(value = {"/all"})
    public ResponseEntity<List<Client>> getAllClients() {
        log.info("ENDPOINT : getAllClients");
        List<Client> clientList = clientRepository.findAll();

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}
