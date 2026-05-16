package com.example.spring.services;

import com.example.spring.entities.Participation;
import com.example.spring.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository repository;

    @Override
    public Participation save(Participation participation) {
        participation.setStatut("EN_ATTENTE"); // par défaut
        return repository.save(participation);
    }

    @Override
    public List<Participation> getAll() {
        return repository.findAll();
    }

    @Override
    public Participation getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participation introuvable"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}