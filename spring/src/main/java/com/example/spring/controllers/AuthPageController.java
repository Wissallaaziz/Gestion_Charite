package com.example.spring.controllers;

import com.example.spring.entities.Utilisateur;
import com.example.spring.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthPageController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String motDePasse,
            HttpSession session,
            Model model
    ) {
        try {
            Utilisateur user = utilisateurService.login(email, motDePasse);
            session.setAttribute("currentUser", user);
            return "redirect:/";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("email", email);
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String nom,
            @RequestParam String email,
            @RequestParam String motDePasse,
            @RequestParam(defaultValue = "USER") String role,
            Model model
    ) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(motDePasse);
        utilisateur.setRole(com.example.spring.entities.Role.valueOf(role.toUpperCase()));

        try {
            utilisateurService.register(utilisateur);
            return "redirect:/login?registered=true";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("nom", nom);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
