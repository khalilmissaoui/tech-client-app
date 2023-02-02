package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Agency;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;



import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class AgencyRepositoryTest {

    @Autowired
    AgencyRepository agencyRepository;


    @BeforeAll
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
        List<Agency> agencies = agencyRepository.findByDescriptionNotNull();


        agencies.stream().forEach(
                agency -> assertThat(agency.getDescription()).isNotNull()
        );

        log.info("--Described agencies total are --> :"+agencies.size());
    }



    @Test
    public void getAgenciesByLocation () {
        String LOCATION = "Paris west" ;
        List<Agency> agencies = agencyRepository.findByLocalisation(LOCATION);



        agencies.stream().forEach(
                agency -> assertThat(agency.getLocalisation().equals(LOCATION)).isTrue()
        );

        log.info("--agencies in " + LOCATION +" total are --> :"+agencies.size());
    }

    @Test
    public void getAgenciesByName () {
        String NAME = "1" ;
        List<Agency> agencies = agencyRepository.findByNameContaining(NAME);



        agencies.stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.size());
    }


    @Test
    public void getAgenciesByNameNative () {
        String NAME = "Agence 2" ;
        List<Agency> agencies = agencyRepository.getAgencyByNameNative(NAME);



        agencies.stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.size());
    }

    @Test
    public void getAgencyByID () throws Exception {
        Long ID = agencyRepository.findAll().get(0).getAgenceId();

        Agency agency = agencyRepository.getAgencyByItsId(ID).orElseThrow(() -> new Exception("EMPTY LIST OF AGENCIES"));



        assertThat(agency.getAgenceId().equals(ID)).isTrue();

        log.info("--agency ID is  --> :"+agency.getAgenceId());
    }
}

