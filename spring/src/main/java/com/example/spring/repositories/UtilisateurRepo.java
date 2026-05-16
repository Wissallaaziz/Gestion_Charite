package com.example.spring.repositories;

import com.example.spring.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

}