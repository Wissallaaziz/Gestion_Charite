package com.example.spring.controllers;

import com.example.spring.entities.Organisation;
import com.example.spring.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organisations")
@CrossOrigin("*")
public class OrganisationApiController {

    @Autowired
    private OrganisationService organisationService;

    @GetMapping
    public List<Organisation> getAll() {
        return organisationService.getAllOrganisations();
    }

    @GetMapping("/{id}")
    public Organisation getById(@PathVariable Long id) {
        return organisationService.getOrganisationById(id);
    }

    @PostMapping
    public Organisation create(@RequestBody Organisation organisation) {
        return organisationService.saveOrganisation(organisation);
    }

    @PutMapping("/{id}")
    public Organisation update(@PathVariable Long id, @RequestBody Organisation organisation) {
        return organisationService.updateOrganisation(id, organisation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        organisationService.deleteOrganisation(id);
    }
}
