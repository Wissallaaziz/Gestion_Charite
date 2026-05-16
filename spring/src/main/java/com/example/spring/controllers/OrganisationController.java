package com.example.spring.controllers;

import com.example.spring.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @GetMapping("/organisations")
    public String organisations(Model model) {
        model.addAttribute("organisations", organisationService.getAllOrganisations());
        return "organisations";
    }
}