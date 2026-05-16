package com.example.spring.services;

import com.example.spring.entities.Participation;

import java.util.List;

public interface ParticipationService {

    Participation save(Participation participation);

    List<Participation> getAll();

    Participation getById(Long id);

    void delete(Long id);
}