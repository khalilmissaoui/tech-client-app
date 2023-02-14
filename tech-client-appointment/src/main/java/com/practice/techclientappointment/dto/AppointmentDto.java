package com.practice.techclientappointment.dto;

import lombok.AllArgsConstructor;
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
public class AppointmentDto {

    enum Location {
        US,
        EU
    }

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @NotNull(message = "appointmentId should not be null")
    private Long appointmentId;

    @NotBlank(message = "price should not be empty string")
    private String price;

    @NotNull
    private Location location;


    @NotNull
    private String time;

    @NotNull
    private Long clientId;


    @NotNull
    private Long techId;


    private Date parseStringToDate(String timezone) throws ParseException {
        return dateFormat.parse(timezone);
    }

    private String stringifyDate(Date date) {

        return date.toString();
    }

/*    private String convertPrice(String price) {

    }*/

}
