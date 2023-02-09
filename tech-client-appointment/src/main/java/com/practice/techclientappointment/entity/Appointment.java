package com.practice.techclientappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "T_APPOINTMENT")

public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull

    private Long appointmentId;

    @NotBlank(message = "price should not be empty string")
    @Size(min = 2, max = 10 , message = "price should not be less than 2 digits")
    private String price;

    @Temporal (TemporalType.DATE)
    private Date time ;


    @ManyToOne(
            optional = false
            ,cascade=CascadeType.ALL
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "clientId"
    )
    private Client client;

    @ManyToOne(optional = false
            ,cascade=CascadeType.ALL

    )
    @JoinColumn(
            name = "tech_id",
            referencedColumnName = "techId"
    )
    private Technician technician;

}
