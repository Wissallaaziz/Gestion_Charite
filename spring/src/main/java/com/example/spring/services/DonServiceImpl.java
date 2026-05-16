package com.example.spring.services;

import com.example.spring.entities.Don;
import com.example.spring.repositories.DonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonServiceImpl implements DonService {

    @Autowired
    private DonRepository repository;

    @Override
    public Don save(Don don) {
        return repository.save(don);
    }

    @Override
    public List<Don> getAll() {
        return repository.findAll();
    }

    @Override
    public Don getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Don introuvable"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<Long, Double> sumDonationsByActionForUser(Long userId) {
        Map<Long, Double> totalsByAction = new HashMap<>();
        for (Object[] row : repository.sumDonationsByActionForUser(userId)) {
            Long actionId = (Long) row[0];
            Double total = ((Number) row[1]).doubleValue();
            totalsByAction.put(actionId, total);
        }
        return totalsByAction;
    }
}