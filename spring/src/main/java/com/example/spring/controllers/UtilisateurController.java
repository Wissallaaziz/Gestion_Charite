package com.example.spring.controllers;
import com.example.spring.DTO.LoginRequest;
import com.example.spring.DTO.LoginResponse;
import com.example.spring.entities.Utilisateur;
import com.example.spring.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // CREATE
    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        String role = utilisateur.getRole();
        if (role == null || role.isBlank()) {
            utilisateur.setRole("USER");
        } else {
            role = role.trim().toUpperCase(Locale.ROOT);
            if (!role.equals("USER") && !role.equals("ORGANISATION")) {
                throw new RuntimeException("Role non autorise pour la creation de compte");
            }
            utilisateur.setRole(role);
        }
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    // READ ALL
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id,
                                         @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

    // GET BY EMAIL
    @GetMapping("/email/{email}")
    public Utilisateur getUtilisateurByEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Utilisateur user = utilisateurService.login(
                request.getEmail(),
                request.getMotDePasse()
        );

        return new LoginResponse("Login réussi", user);
    }
    @PostMapping("/register")
    public Utilisateur register(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.register(utilisateur);
    }
}