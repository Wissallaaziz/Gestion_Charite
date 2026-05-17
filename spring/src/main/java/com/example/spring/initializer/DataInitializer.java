package com.example.spring.initializer;

import com.example.spring.entities.Role;
import com.example.spring.entities.Utilisateur;
import com.example.spring.repositories.UtilisateurRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepo utilisateurRepository;

    public DataInitializer(UtilisateurRepo utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void run(String... args) {

        if (utilisateurRepository.findByEmail("admin@gmail.com").isEmpty()) {

            Utilisateur admin = new Utilisateur();
            admin.setNom("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("1234");
            admin.setRole(Role.ADMIN);
            admin.setDateCreation(new Date());

            utilisateurRepository.save(admin);
        }
    }
}