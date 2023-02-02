package com.practice.techclientappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private Long appointmentId;

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
