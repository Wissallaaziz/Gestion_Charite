package com.example.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;
    private String role;
    private Date dateCreation;

    @OneToMany(mappedBy = "utilisateur")
    private List<Don> dons;

    @OneToMany(mappedBy = "utilisateur")
    private List<Participation> participations;

}