package com.practice.techclientappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "T_AGENCE")
public class Agency {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long agenceId;

    private String localisation;

    @Column(
            name = "agence_nom",
            nullable = false
    )
    private String name ;


    private String description ;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "agency"
    )
    List<Technician> technicians;



}
