package com.example.spring.controllers;

import com.example.spring.entities.Admin;
import com.example.spring.entities.Organisation;
import com.example.spring.services.AdminService;
import com.example.spring.services.OrganisationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final OrganisationService organisationService;
    private final AdminService adminService;

    public AdminController(OrganisationService organisationService,
                           AdminService adminService) {
        this.organisationService = organisationService;
        this.adminService = adminService;
    }

    // LOGIN ADMIN
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        Admin admin = adminService.login(email, password);

        if (admin != null) {
            return "Login OK";
        }

        return "Email ou mot de passe incorrect";
    }

    // voir les organisations non validées
    @GetMapping("/organisations/pending")
    public List<Organisation> getPending() {
        return organisationService.getNonValidees();
    }

    // valider une organisation
    @PutMapping("/organisations/{id}/valider")
    public Organisation valider(@PathVariable Long id) {
        return organisationService.validerOrganisation(id);
    }
}