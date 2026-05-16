package com.example.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class ActionCharite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String lieu;
    private double objectifMontant;
    private double montantCollecte;
    private String statut;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    private Organisation organisation;

    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "actionCharite")
    private List<Don> dons;

    @OneToMany(mappedBy = "actionCharite")
    private List<Participation> participations;

    @OneToMany(mappedBy = "actionCharite")
    private List<Media> medias;

    public double getObjectif() {
        return objectifMontant;
    }

    public void setObjectif(double objectif) {
        this.objectifMontant = objectif;
    }
}