package com.example.spring.services;

import com.example.spring.entities.Organisation;
import com.example.spring.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationById(Long id) {
        return organisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organisation non trouvée avec id : " + id));
    }

    @Override
    public Organisation updateOrganisation(Long id, Organisation organisation) {

        Organisation org = getOrganisationById(id);

        org.setNom(organisation.getNom());
        org.setAdresse(organisation.getAdresse());
        org.setContact(organisation.getContact());
        org.setDescription(organisation.getDescription());

        return organisationRepository.save(org);
    }

    @Override
    public void deleteOrganisation(Long id) {
        organisationRepository.deleteById(id);
    }
    @Override
    public Organisation validerOrganisation(Long id) {
        Organisation org = getOrganisationById(id);
        org.setValide(true);
        return organisationRepository.save(org);
    }

    @Override
    public List<Organisation> getNonValidees() {
        return organisationRepository.findByValideFalse();
    }
}