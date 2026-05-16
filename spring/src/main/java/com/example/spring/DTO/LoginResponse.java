package com.example.spring.DTO;

import com.example.spring.entities.Utilisateur;

public class LoginResponse {

    private String message;
    private Utilisateur utilisateur;

    public LoginResponse(String message, Utilisateur utilisateur) {
        this.message = message;
        this.utilisateur = utilisateur;
    }

    // getters
}