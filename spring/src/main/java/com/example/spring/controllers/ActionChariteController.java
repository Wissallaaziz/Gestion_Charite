package com.example.spring.controllers;

import com.example.spring.entities.ActionCharite;
import com.example.spring.entities.Utilisateur;
import com.example.spring.services.ActionChariteService;
import com.example.spring.services.DonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ActionChariteController {

    @Autowired
    private ActionChariteService actionService;

    @Autowired
    private DonService donService;

    @GetMapping("/actions")
    public String actions(@RequestParam(required = false) String category, Model model, HttpSession session) {
        List<ActionCharite> actions = actionService.getAll();
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");

        if (category != null && !category.isBlank()) {
            actions = actions.stream()
                    .filter(a -> a.getCategorie() != null)
                    .filter(a -> category.equalsIgnoreCase(a.getCategorie().getNom()))
                    .toList();
        }

        model.addAttribute("actions", actions);
        model.addAttribute("selectedCategory", category);

        Map<Long, Double> donationByAction = Map.of();
        if (currentUser != null && "USER".equalsIgnoreCase(currentUser.getRole())) {
            donationByAction = donService.sumDonationsByActionForUser(currentUser.getId());
        }
        model.addAttribute("donationByAction", donationByAction);
        model.addAttribute("isDonorUser", currentUser != null && "USER".equalsIgnoreCase(currentUser.getRole()));
        return "actions";
    }

    @GetMapping("/actions/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("action", actionService.getById(id));
        return "action-details";
    }

    @GetMapping("/actions/form")
    public String form(Model model, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (!"ORGANISATION".equalsIgnoreCase(currentUser.getRole())) {
            return "redirect:/actions";
        }
        model.addAttribute("action", new ActionCharite());
        return "action-form";
    }

    @PostMapping("/actions/save")
    public String save(@ModelAttribute ActionCharite action, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (!"ORGANISATION".equalsIgnoreCase(currentUser.getRole())) {
            return "redirect:/actions";
        }
        actionService.save(action);
        return "redirect:/actions";
    }
}