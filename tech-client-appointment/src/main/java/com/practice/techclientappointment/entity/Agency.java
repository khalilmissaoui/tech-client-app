package com.practice.techclientappointment.entity;

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
@Table( name = "T_AGENCE")
public class Agency {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long agenceId;

    private String localisation;

    @Column(
            name = "agence_nom",
            nullable = false
    )
    private String name ;


    private String description ;



}
