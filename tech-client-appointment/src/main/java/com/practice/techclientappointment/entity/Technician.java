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
@Table( name = "T_TECHNICIEN",
        uniqueConstraints = @UniqueConstraint(
        name = "unique_attributes_tech",
        columnNames = {"number" , "personalPhoneNumber"}
        )


)
public class Technician {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long techId;

    private String firstName ;
    private String lastName ;

    @Column(

            name = "number",
            nullable = false
    )
    private String phoneNumber ;



    private String personalPhoneNumber ;

    private String speciality ;

    private String Zone ;
    @Transient
    private Boolean isMarried;

    private Boolean isAvailable;


    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "agency_id",
            referencedColumnName = "agenceId"
    )
    private Agency agency;

    @OneToMany()
    private List<Appointment>  appointment;
}
