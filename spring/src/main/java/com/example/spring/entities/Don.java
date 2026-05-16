package com.example.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class Don {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private Date date;
    private String methodePaiement;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private ActionCharite actionCharite;

    public void setAction(ActionCharite action) {
        this.actionCharite = action;
    }

    // Getters & Setters
}