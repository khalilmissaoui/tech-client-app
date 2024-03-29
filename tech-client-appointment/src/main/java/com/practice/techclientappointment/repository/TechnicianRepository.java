package com.practice.techclientappointment.repository;

import com.practice.techclientappointment.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {


    Optional<Technician> findFirstByIsAvailableIsTrue();

    Optional<Technician> findFirstByIsAvailableIsFalse();

    Optional<Technician> findFirstByIsAvailableIsNull();

    Optional<Technician> findByTechId(Long id);

    Optional<Technician> findFirstByFirstName(String firstName);

    List<Technician> findByIsAvailableIsNull();


    @Modifying
    @Transactional
    @Query(
            value = "update t_technicien set is_available = 1 where tech_id = ?1",
            nativeQuery = true
    )
    int updateTechStateToAvailable(Long id);


    @Modifying
    @Transactional
    @Query(
            value = "update t_technicien set is_available = 0 where tech_id = ?1",
            nativeQuery = true
    )
    int updateTechStateToUnavailable(Long id);


    @Modifying
    @Transactional
    @Query(
            value = "update t_technicien set first_name = ?1 where tech_id = ?2",
            nativeQuery = true
    )
    int updateFirstNameByID(String firstName, Long id);


}
