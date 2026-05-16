package com.example.spring.controllers;

import com.example.spring.entities.Participation;
import com.example.spring.entities.ActionCharite;
import com.example.spring.services.ParticipationService;
import com.example.spring.services.ActionChariteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private ActionChariteService actionService;

    @GetMapping("/participation/form/{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("actionId", id);
        return "participation-form";
    }

    @PostMapping("/participation/save")
    public String save(@RequestParam Long actionId) {

        ActionCharite action = actionService.getById(actionId);

        Participation p = new Participation();
        p.setAction(action);

        participationService.save(p);

        return "redirect:/actions";
    }
}