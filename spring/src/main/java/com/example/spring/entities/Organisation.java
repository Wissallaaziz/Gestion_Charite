package com.example.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String numeroFiscal;
    private String contact;
    private String description;
    private String logo;
    private Date dateCreation;
    private boolean valide = false;
    @OneToMany(mappedBy = "organisation")
    private List<ActionCharite> actions;

    @OneToOne
    private Admin admin;

    // Getters & Setters
}