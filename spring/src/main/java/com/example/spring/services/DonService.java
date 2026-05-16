package com.example.spring.services;

import com.example.spring.entities.Don;

import java.util.List;
import java.util.Map;

public interface DonService {

    Don save(Don don);

    List<Don> getAll();

    Don getById(Long id);

    void delete(Long id);

    Map<Long, Double> sumDonationsByActionForUser(Long userId);
}