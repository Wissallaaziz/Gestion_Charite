package com.example.spring.services;

import com.example.spring.entities.ActionCharite;
import com.example.spring.repositories.ActionChariteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionChariteServiceImpl implements ActionChariteService {

    @Autowired
    private ActionChariteRepository repository;

    @Override
    public ActionCharite save(ActionCharite action) {
        return repository.save(action);
    }

    @Override
    public List<ActionCharite> getAll() {
        return repository.findAll();
    }

    @Override
    public ActionCharite getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action non trouvée"));
    }

    @Override
    public ActionCharite update(Long id, ActionCharite action) {
        ActionCharite a = getById(id);

        a.setTitre(action.getTitre());
        a.setDescription(action.getDescription());
        a.setObjectif(action.getObjectif());
        a.setMontantCollecte(action.getMontantCollecte());
        a.setDateDebut(action.getDateDebut());
        a.setDateFin(action.getDateFin());
        a.setOrganisation(action.getOrganisation());

        return repository.save(a);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}