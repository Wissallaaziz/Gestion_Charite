package com.example.spring.repositories;

import com.example.spring.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    List<Organisation> findByValideFalse();

    List<Organisation> findByValideTrue();
    long countByValideTrue();
}