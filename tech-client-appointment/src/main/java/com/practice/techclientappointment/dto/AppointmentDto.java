package com.practice.techclientappointment.dto;

import com.practice.techclientappointment.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {


    @NotBlank(message = "price should not be empty string")
    private String price;


    @NotNull
    private Date time;

    @NotNull
    private Long clientId;


    @NotNull
    private Long techId;


    public Appointment toEntity() {


        return Appointment.builder()
                .price(this.getPrice())
                .time(this.getTime())
                .build();
    }


}


//projections are an alternative
// add projections to handle retrieved data for tech and client without DTO
// add money api to convert $ to £
//other dto solutions : mapStuck : mapper deps : current way