package com.practice.techclientappointment.entity;

import com.practice.techclientappointment.util.clientType.ClientTypeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "T_CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long clientId;

    @ClientTypeConstraint
    private String type;

    @Embedded
    private Address address;




}
