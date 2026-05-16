package com.example.spring.controllers;

import com.example.spring.entities.Organisation;
import com.example.spring.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private OrganisationService organisationService;

    // 📋 voir les organisations non validées
    @GetMapping("/organisations/pending")
    public List<Organisation> getPending() {
        return organisationService.getNonValidees();
    }

    // ✔ valider une organisation
    @PutMapping("/organisations/{id}/valider")
    public Organisation valider(@PathVariable Long id) {
        return organisationService.validerOrganisation(id);
    }
}