package com.example.spring.services;

import com.example.spring.entities.ActionCharite;

import java.util.List;

public interface ActionChariteService {

    ActionCharite save(ActionCharite action);

    List<ActionCharite> getAll();

    ActionCharite getById(Long id);

    ActionCharite update(Long id, ActionCharite action);

    void delete(Long id);
}