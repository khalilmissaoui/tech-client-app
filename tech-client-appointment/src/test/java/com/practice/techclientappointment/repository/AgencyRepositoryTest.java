package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Agency;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;



import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@ActiveProfiles("test")
class AgencyRepositoryTest {

    @Autowired
    AgencyRepository agencyRepository;


    @BeforeAll
    public void SHOULD_SAVE_TEST_DATA_IN_DB() {
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
    public void SHOULD_RETURN_LIST_TECHNICIANS_AND_ASSERT_SIZE() {

        // WHEN
        List<Agency> agencies = agencyRepository.findAll();

        //THEN
        assertThat(agencies).isNotNull();
  //      assertThat(agencies.size()).isEqualTo(4);
    }
    @Test
    public void SHOULD_RETURN_LIST_OF_AGENCIES_WITH_DESCRIPTIONS_NOT_NULL () {


        // WHEN
        List<Agency> agencies = agencyRepository.findByDescriptionNotNull();


        //THEN
        assertThat(agencies).isNotNull();
        assertThat(agencies.size() > 0).isTrue();
        agencies.stream().forEach(
                agency -> assertThat(agency.getDescription()).isNotNull()
        );

        log.info("--Described agencies total are --> :"+agencies.size());
    }



    @Test
    public void SHOULD_RETURN_LIST_OF_AGENCIES_BY_LOCATION () {

        //GIVEN DATA
        String LOCATION = "Paris west" ;

        // WHEN
        List<Agency> agencies = agencyRepository.findByLocalisation(LOCATION);

        //THEN
        assertThat(agencies).isNotNull();
        assertThat(agencies.size() > 0).isTrue();
        agencies.stream().forEach(
                agency -> assertThat(agency.getLocalisation().equals(LOCATION)).isTrue()
        );

        log.info("--agencies in " + LOCATION +" total are --> :"+agencies.size());
    }

    @Test
    public void SHOULD_RETURN_LIST_OF_AGENCIES_CONTAINS_NAME () {

        //GIVEN DATA
        String NAME = "1" ;

        // WHEN
        List<Agency> agencies = agencyRepository.findByNameContaining(NAME);
        assertThat(agencies).isNotNull();
        assertThat(agencies.size() > 0).isTrue();
        agencies.stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.size());
    }


    @Test
    public void SHOULD_RETURN_LIST_OF_AGENCIES_BY_NAME () {
        //GIVEN DATA
        String NAME = "Agence 2" ;

        // WHEN
        List<Agency> agencies = agencyRepository.getAgencyByNameNative(NAME);

        //THEN
        assertThat(agencies).isNotNull();
        assertThat(agencies.size() > 0).isTrue();
        agencies.stream().forEach(
                agency -> assertThat(agency.getName().contains(NAME)).isTrue()
        );

        log.info("--agencies total are --> :"+agencies.size());
    }

    @Test
    public void SHOULD_RETURN_AGENCY_BY_ID () throws Exception {
        //GIVEN DATA
        Long ID = agencyRepository.findAll().get(0).getAgenceId();

        // WHEN
        Agency agency = agencyRepository.getAgencyByItsId(ID).orElseThrow(() -> new Exception("EMPTY LIST OF AGENCIES"));

        //THEN
        assertThat(agency.getAgenceId().equals(ID)).isTrue();

        log.info("--agency ID is  --> :"+agency.getAgenceId());
    }
}

