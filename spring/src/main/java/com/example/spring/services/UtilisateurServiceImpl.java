package com.example.spring.services;

import com.example.spring.entities.Utilisateur;
import com.example.spring.repositories.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur u = getUtilisateurById(id);

        u.setNom(utilisateur.getNom());
        u.setEmail(utilisateur.getEmail());
        u.setMotDePasse(utilisateur.getMotDePasse());
        u.setRole(utilisateur.getRole());

        return utilisateurRepository.save(u);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
    @Override
    public Utilisateur login(String email, String motDePasse) {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email incorrect"));

        if (!utilisateur.getMotDePasse().equals(motDePasse)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return utilisateur;
    }
    @Override
    public Utilisateur register(Utilisateur utilisateur) {

        // check if email already exists
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Only USER and ORGANISATION can self-register
        String role = utilisateur.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }
        role = role.trim().toUpperCase(Locale.ROOT);

        if (!role.equals("USER") && !role.equals("ORGANISATION")) {
            throw new RuntimeException("Role non autorise pour l'inscription");
        }

        utilisateur.setRole(role);

        // date creation
        utilisateur.setDateCreation(new Date());

        return utilisateurRepository.save(utilisateur);
    }
}