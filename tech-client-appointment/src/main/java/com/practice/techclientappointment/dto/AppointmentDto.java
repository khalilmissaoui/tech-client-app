package com.practice.techclientappointment.dto;

import com.practice.techclientappointment.entity.Appointment;
import com.practice.techclientappointment.entity.Client;
import com.practice.techclientappointment.entity.Technician;
import com.practice.techclientappointment.exceptions.NotFoundException;
import com.practice.techclientappointment.repository.ClientRepository;
import com.practice.techclientappointment.repository.TechnicianRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {


    ClientRepository clientRepository;
    TechnicianRepository technicianRepository;


    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @NotBlank(message = "price should not be empty string")
    private String price;


    @NotNull
    private String time;

    @NotNull
    private String clientId;


    @NotNull
    private String techId;


    private Date parseStringToDate(String timezone) throws ParseException {
        return dateFormat.parse(timezone);
    }

    private String stringifyDate(Date date) {

        return date.toString();
    }

    public Appointment toEntity(AppointmentDto appointmentDto) throws Exception {
        if (appointmentDto == null) {
            throw new NotFoundException();
        }
        //light solution to test if entity exist : still can use repo.getbyid ; it does implements getreference implicitly
        Technician technicianFound = technicianRepository.getReferenceById(Long.parseLong(appointmentDto.getTechId()));
        Client clientFound = clientRepository.getReferenceById(Long.parseLong(appointmentDto.getClientId()));

        return Appointment.builder()
                .price(appointmentDto.getPrice())
                .time(parseStringToDate(appointmentDto.getTime()))
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
                .time(stringifyDate(appointment.getTime()))
                .clientId(appointment.getClient().getClientId().toString())
                .techId(appointment.getTechnician().getTechId().toString())
                .build();
    }
}


//projections are an alternative
// add projections to handle retrieved data for tech and client without DTO

//other dto solutions : mapStuck : mapper deps : current way