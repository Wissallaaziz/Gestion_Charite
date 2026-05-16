package com.example.spring.services;

import com.example.spring.entities.Organisation;

import java.util.List;

public interface OrganisationService {

    Organisation saveOrganisation(Organisation organisation);

    List<Organisation> getAllOrganisations();

    Organisation getOrganisationById(Long id);

    Organisation updateOrganisation(Long id, Organisation organisation);

    void deleteOrganisation(Long id);
    Organisation validerOrganisation(Long id);
    List<Organisation> getNonValidees();
}