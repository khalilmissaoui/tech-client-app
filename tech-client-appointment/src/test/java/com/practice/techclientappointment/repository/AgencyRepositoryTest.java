package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Agency;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
class AgencyRepositoryTest {

    @Autowired
    AgencyRepository agencyRepository;


    @Test
    public void saveAgency() {
        Agency agency1 = Agency.builder()
                .name("Agence 1")
                .localisation("Paris sud")
                .build();

        Agency agency2 = Agency.builder()
                .name("Agence 2")
                .localisation("Paris nord")

                .build();

        Agency agency3 = Agency.builder()
                .name("Agence 3")
                .localisation("Paris west")
                .description("agence three description")
                .build();

        Agency agency4 = Agency.builder()
                .name("Agence 4")
                .localisation("Paris east")
                .description("random description")
                .build();

        agencyRepository.save(agency1);
        agencyRepository.save(agency2);
        agencyRepository.save(agency3);
        agencyRepository.save(agency4);

    }

    @Test
    public void getDescribedAgencies () {
        Optional<List<Agency>> agencies = Optional.of(agencyRepository.findByDescriptionNotNull());


        agencies.get().stream().forEach(
                agency -> assertThat(agency.getDescription()).isNotNull()
        );

        log.info("--Described agencies total are --> :"+agencies.get().size());
    }



    @Test
    public void getAgenciesByLocation () {
        String LOCATION = "Paris west" ;
        Optional<List<Agency>> agencies = Optional.of(agencyRepository.findByLocalisation(LOCATION));



        agencies.get().stream().forEach(
                agency -> assertThat(agency.getLocalisation().equals(LOCATION)).isTrue()
        );

        log.info("--agencies in " + LOCATION +" total are --> :"+agencies.get().size());
    }

    @Test
    public void getAgenciesByName () {
        String NAME = "1" ;
        Optional<List<Agency>> agencies = Optional.of(agencyRepository.findByNameContaining(NAME));



        agencies.get().stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.get().size());
    }


    @Test
    public void getAgenciesByNameNative () {
        String NAME = "Agence 2" ;
        Optional<List<Agency>> agencies = Optional.of(agencyRepository.getAgencyByNameNative(NAME));



        agencies.get().stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.get().size());
    }

    @Test
    public void getAgencyByID () throws Exception {
        Long ID = agencyRepository.findAll().get(0).getAgenceId();

        Agency agency = agencyRepository.getAgencyByItsId(ID).orElseThrow(() -> new Exception("EMPTY LIST OF AGENCIES"));



        assertThat(agency.getAgenceId().equals(ID)).isTrue();

        log.info("--agency ID is  --> :"+agency.getAgenceId());
    }
}

