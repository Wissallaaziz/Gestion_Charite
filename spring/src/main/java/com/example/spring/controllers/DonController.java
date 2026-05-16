package com.example.spring.controllers;

import com.example.spring.entities.Don;
import com.example.spring.entities.ActionCharite;
import com.example.spring.entities.Utilisateur;
import com.example.spring.services.DonService;
import com.example.spring.services.ActionChariteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DonController {

    @Autowired
    private DonService donService;

    @Autowired
    private ActionChariteService actionService;

    @GetMapping("/don/form/{id}")
    public String form(@PathVariable Long id, Model model, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("don", new Don());
        model.addAttribute("actionId", id);
        return "don-form";
    }

    @PostMapping("/don/save")
    public String save(@ModelAttribute Don don, @RequestParam Long actionId, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        ActionCharite action = actionService.getById(actionId);
        don.setAction(action);
        don.setUtilisateur(currentUser);
        don.setDate(new java.util.Date());

        donService.save(don);
        action.setMontantCollecte(action.getMontantCollecte() + don.getMontant());
        actionService.save(action);

        return "redirect:/actions";
    }
}