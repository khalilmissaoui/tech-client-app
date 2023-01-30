package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Agency;
import com.practice.techclientappointment.entity.Technician;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;




@SpringBootTest
@Slf4j
class TechnicianRepositoryTest {


    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    AgencyRepository agencyRepository;

  @Test
    public void saveTechnician() {
        Long ID = 1L;
        Optional<Agency> agency = Optional.of(agencyRepository.getAgencyByItsId(ID));

        Technician tech1 = Technician.builder()
                .firstName("rami")
                .lastName("petit")
                .phoneNumber("14247733")
                .personalPhoneNumber("1772398842")
                .Zone("C")
                .speciality("electrique")
                .agency(agency.get())
                .isAvailable(true)
                .build();


        Technician tech2 = Technician.builder()
                .firstName("tech2")
                .lastName("last2")
                .phoneNumber("2342399")
                .personalPhoneNumber("2328842")
                .Zone("D")
                .speciality("batiment")
                .agency(agency.get())
                .isAvailable(false)
                .build();


        Technician tech3 = Technician.builder()
                .firstName("tech3")
                .lastName("bernat")
                .phoneNumber("34200033")
                .personalPhoneNumber("3233000842")
                .Zone("B")
                .speciality("electrique")
                .agency(agency.get())
                .isAvailable(false)
                .build();


        Technician tech4 = Technician.builder()
                .firstName("tech4")
                .lastName("juan")
                .phoneNumber("42342322433")
                .personalPhoneNumber("41233398842")
                .Zone("H")
                .speciality("electrique")
                .agency(agency.get())
                .isAvailable(true)
                .build();

      Technician tech5 = Technician.builder()
              .firstName("tech5")
              .lastName("leonardo")
              .phoneNumber("542342322433")
              .personalPhoneNumber("541233398842")
              .Zone("H")
              .speciality("electrique")
              .agency(agency.get())
              .build();
        technicianRepository.save(tech1);
        technicianRepository.save(tech2);
        technicianRepository.save(tech3);
        technicianRepository.save(tech4);
        technicianRepository.save(tech5);

    }


    @Test
    public void getTechBlurryStateList () {
        Optional<List<Technician>> technicians = Optional.of(technicianRepository.findByIsAvailableIsNull());


        technicians.get().forEach(
                technician -> assertThat(technician.getIsAvailable()).isNull()
        );

        log.info("--technicians with unclear state count --> :"+technicians.get().size());

    }


    @Test()
    public void setTechStateToAvailable () throws Exception {
        Optional<Technician> technician = Optional.of(technicianRepository.findFirstByIsAvailableIsFalse());

        Long ID = technician.orElseThrow(() -> new Exception("TECH not found")).getTechId();

        assertThat(technician.get().getIsAvailable()).isFalse();

        technicianRepository.updateTechStateToAvailable(ID);

        assertThat(technicianRepository.findByTechId(ID).getIsAvailable()).isTrue();


        log.info("--technician with the ID  --> :"+technician.get().getTechId() + " UPDATE state to AVAILABLE ");

    }

    @Test()
    public void setTechStateToUnavailable () throws Exception {
        Optional<Technician> technician = Optional.of(technicianRepository.findFirstByIsAvailableIsNull());

        Long ID = technician.orElseThrow(() -> new Exception("TECH not found")).getTechId();

        assertThat(technician.get().getIsAvailable()).isNull();

        technicianRepository.updateTechStateToUnavailable(ID);

        assertThat(technicianRepository.findByTechId(ID).getIsAvailable()).isFalse();


        log.info("--technician with the ID  --> :"+technician.get().getTechId() + " UPDATE state to UNAVAILABLE ");

    }
    @Test()
    public void setTechFirstName ()  {

        String firstName = "tech2";
        String newName = "random new name for test";

        Optional<Technician> technician = Optional.of(technicianRepository.findFirstByFirstName(firstName));

        Long ID = technician.get().getTechId();

        technicianRepository.updateFirstNameByID(newName,ID);
        assertThat(technicianRepository.findByTechId(ID).getFirstName()).isEqualTo(newName);



        log.info("--technician with the ID  --> :"+ID + " UPDATE FIRSTNAME to :  "+technician.get().getFirstName());

    }

}