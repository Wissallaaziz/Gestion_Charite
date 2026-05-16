package com.example.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateParticipation;
    private String statut;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private ActionCharite actionCharite;

    public void setAction(ActionCharite action) {
    }

    // Getters & Setters
}