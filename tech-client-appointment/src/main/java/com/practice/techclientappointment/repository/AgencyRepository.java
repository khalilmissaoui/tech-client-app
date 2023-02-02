package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {



    List<Agency> findByNameContaining(String name);

    List<Agency> findByLocalisation(String location);

    List<Agency> findByDescriptionNotNull();


    //JPQL
    @Query("select agence from Agency agence where agence.agenceId = ?1")
    Optional <Agency>  getAgencyByItsId(Long id);
    //SQL QUERY
    @Query(
            value = "SELECT * FROM t_agence ta WHERE ta.agence_nom = ?1",
            nativeQuery = true
    )
    List<Agency> getAgencyByNameNative(String agencyName);

}
