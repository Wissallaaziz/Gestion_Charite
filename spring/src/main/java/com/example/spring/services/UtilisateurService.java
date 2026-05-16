package com.example.spring.services;

import com.example.spring.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getAllUtilisateurs();

    Utilisateur getUtilisateurById(Long id);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);

    void deleteUtilisateur(Long id);

    Utilisateur getUtilisateurByEmail(String email);
    Utilisateur login(String email, String motDePasse);
    Utilisateur register(Utilisateur utilisateur);
}