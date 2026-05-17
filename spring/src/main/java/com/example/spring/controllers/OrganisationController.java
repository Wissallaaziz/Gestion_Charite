package com.example.spring.controllers;

import com.example.spring.entities.Organisation;
import com.example.spring.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @GetMapping("/organisations")
    public String organisations(Model model, @RequestParam(required = false) Long editId) {
        Organisation organisation = new Organisation();
        String formAction = "/organisations/save";
        String formTitle = "Créer une organisation";
        String submitLabel = "Créer";

        if (editId != null) {
            organisation = organisationService.getOrganisationById(editId);
            formAction = "/organisations/update/" + editId;
            formTitle = "Modifier l'organisation";
            submitLabel = "Enregistrer";
        }

        model.addAttribute("organisations", organisationService.getAllOrganisations());
        model.addAttribute("organisation", organisation);
        model.addAttribute("formAction", formAction);
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("submitLabel", submitLabel);
        return "organisations";
    }

    @PostMapping("/organisations/save")
    public String saveOrganisation(@ModelAttribute Organisation organisation) {
        if (organisation.getDateCreation() == null) {
            organisation.setDateCreation(new Date());
        }
        organisationService.saveOrganisation(organisation);
        return "redirect:/organisations";
    }

    @PostMapping("/organisations/update/{id}")
    public String updateOrganisation(@PathVariable Long id, @ModelAttribute Organisation organisation) {
        organisationService.updateOrganisation(id, organisation);
        return "redirect:/organisations";
    }

    @GetMapping("/organisations/delete/{id}")
    public String deleteOrganisation(@PathVariable Long id) {
        organisationService.deleteOrganisation(id);
        return "redirect:/organisations";
    }

    @GetMapping("/organisations/approve/{id}")
    public String approveOrganisation(@PathVariable Long id) {
        organisationService.validerOrganisation(id);
        return "redirect:/organisations";
    }
}