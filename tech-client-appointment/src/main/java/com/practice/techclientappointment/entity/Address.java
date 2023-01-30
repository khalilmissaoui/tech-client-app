package com.practice.techclientappointment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "city",
                column = @Column(name = "client_city")
        ),
        @AttributeOverride(
                name = "street",
                column = @Column(name = "client_street")
        ),
        @AttributeOverride(
                name = "houseNumber",
                column = @Column(name = "client_house_number")
        )
})
public class Address {

    private String city;
    private String street;
    private String houseNumber;

}
