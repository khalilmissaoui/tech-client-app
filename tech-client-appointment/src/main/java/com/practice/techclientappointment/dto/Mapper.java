package com.practice.techclientappointment.dto;

import com.practice.techclientappointment.dto.dtos.AppointmentDto;
import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.exceptions.NotFoundException;
import com.practice.techclientappointment.repository.ClientRepository;
import com.practice.techclientappointment.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TechnicianRepository technicianRepository;


    public Appointment toEntity(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new NotFoundException();
        }

        //light solution to test if entity exist
        Technician technicianFound = technicianRepository.getReferenceById(Long.parseLong(appointmentDto.getTechId()));
        Client clientFound = clientRepository.getReferenceById(Long.parseLong(appointmentDto.getClientId()));


        return Appointment.builder()
                .price(appointmentDto.getPrice())
                .time(appointmentDto.getTime())
                .client(clientFound)
                .technician(technicianFound)
                .build();
    }


    public AppointmentDto toDTO(Appointment appointment) {

        if (appointment == null) {
            throw new NotFoundException();
        }
        return AppointmentDto.builder()
                .price(appointment.getPrice())
                .time(appointment.getTime())
                .clientId(appointment.getClient().getClientId().toString())
                .techId(appointment.getTechnician().getTechId().toString())
                .build();
    }
}
