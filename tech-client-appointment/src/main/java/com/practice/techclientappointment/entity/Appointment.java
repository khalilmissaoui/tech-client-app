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
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long appointmentId;

    private String price;

    @Temporal (TemporalType.DATE)
    private Date time ;


    @ManyToOne()
    private Client client;

    @ManyToOne()
    private Technician technician;

}
