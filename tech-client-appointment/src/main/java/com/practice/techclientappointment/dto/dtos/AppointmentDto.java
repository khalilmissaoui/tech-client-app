package com.practice.techclientappointment.dto.dtos;

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


    private Long id;

    @NotBlank(message = "price should not be empty string")
    private String price;


    @NotNull(message = "time should not be null")
    private Date time;

    @NotNull(message = "clientId should not be null")
    private String clientId;


    @NotNull(message = "techId should not be null")
    private String techId;

//    public Appointment toEntity() {
//
//
//        return Appointment.builder()
//                .price(this.getPrice())
//                .time(this.getTime())
//                .build();
//    }
    
}


//projections are an alternative
// add projections to handle retrieved data for tech and client without DTO
// add money api to convert $ to £
//other dto solutions : mapStuck : mapper deps : current way