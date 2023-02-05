package com.practice.techclientappointment.repository;


import com.practice.techclientappointment.entity.Technician;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;


import static org.assertj.core.api.Assertions.*;




@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class TechnicianRepositoryTest {


    @Autowired
    TechnicianRepository technicianRepository;


  @BeforeAll
    public void SHOULD_SAVE_TECHNICIAN_DATA_IN_DB() throws Exception {


        Technician tech1 = Technician.builder()
                .firstName("rami")
                .lastName("petit")
                .phoneNumber("14247733")
                .personalPhoneNumber("1772398842")
                .Zone("C")
                .speciality("electrique")
                .isAvailable(true)
                .build();


        Technician tech2 = Technician.builder()
                .firstName("tech2")
                .lastName("last2")
                .phoneNumber("2342399")
                .personalPhoneNumber("2328842")
                .Zone("D")
                .speciality("batiment")

                .isAvailable(false)
                .build();


        Technician tech3 = Technician.builder()
                .firstName("tech3")
                .lastName("bernat")
                .phoneNumber("34200033")
                .personalPhoneNumber("3233000842")
                .Zone("B")
                .speciality("electrique")

                .isAvailable(false)
                .build();


        Technician tech4 = Technician.builder()
                .firstName("tech4")
                .lastName("juan")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .isAvailable(true)
                .build();

      Technician tech5 = Technician.builder()
              .firstName("tech5")
              .lastName("leonardo")
              .phoneNumber("542342322433")
              .personalPhoneNumber("541233398842")
              .Zone("H")
              .speciality("electrique")
              .build();



      technicianRepository.save(tech1);
      technicianRepository.save(tech2);
      technicianRepository.save(tech3);
      technicianRepository.save(tech4);
      technicianRepository.save(tech5);


    }

    @Test
    public void SHOULD_RETURN_LIST_TECHNICIANS_AND_ASSERT_SIZE() {

      //WHEN
      List<Technician> technicians = technicianRepository.findAll();

      //THEN
      assertThat(technicians).isNotNull();
      assertThat(technicians.size()).isEqualTo(5);
    }

    @Test
    public void SHOULD_RETURN_LIST_TECHNICIANS_WITH_NULL_ISAVAILABLE () {

        //WHEN
        List<Technician> technicians = technicianRepository.findByIsAvailableIsNull();

        //THEN
        assertThat(technicians).isNotNull();
        assertThat(technicians.size() > 0).isTrue();
        technicians.forEach(
                technician -> assertThat(technician.getIsAvailable()).isNull()
        );

        log.info("--technicians with unclear state count --> :"+technicians.size());

    }


    @Test()
    public void SHOULD_RETURN_FIRST_TECHNICIAN_WITH_ISAVAILABLE_FALSE_OR_THROW_EXCEPTION () throws Exception {

        //WHEN
        Technician technician = technicianRepository.findFirstByIsAvailableIsFalse().orElseThrow(() -> new Exception("TECH not found"));

        //GIVEN
        Long ID = technician.getTechId();

        //THEN
        assertThat(technician.getIsAvailable().compareTo(Boolean.TRUE)).isEqualTo(-1);
        technicianRepository.updateTechStateToAvailable(ID);
        assertThat(technicianRepository.findByTechId(ID).isPresent()).isTrue();


        log.info("--technician with the ID  --> :"+technician.getTechId() + " UPDATE state to AVAILABLE ");

    }

    @Test()
    public void SHOULD_RETURN_FIRST_TECHNICIAN_WITH_ISAVAILABLE_TRUE_OR_THROW_EXCEPTION () throws Exception {

        //WHEN
        Technician technician = technicianRepository.findFirstByIsAvailableIsTrue().orElseThrow(() -> new Exception("TECH not found"));

        //GIVEN
        Long ID = technician.getTechId();

        //THEN
        assertThat(technician.getIsAvailable()).isTrue();
        technicianRepository.updateTechStateToUnavailable(ID);
        assertThat(technicianRepository.findByTechId(ID).isPresent()).isTrue();


        log.info("--technician with the ID  --> :"+technician.getTechId() + " UPDATE state to UNAVAILABLE ");

    }


}