package com.practice.techclientappointment.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ErrorMessage {

    @NonNull
    private String serviceSource;
    @NonNull
    private String errorMessage;


    public String getMessage() {
        return this.getServiceSource() + " - " + this.getErrorMessage();
    }
}
