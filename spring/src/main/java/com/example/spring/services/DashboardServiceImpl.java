package com.example.spring.services;

import com.example.spring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DonRepository donRepository;

    @Autowired
    private ActionChariteRepository actionRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public Double totalDons() {
        Double sum = donRepository.totalDons();
        return sum != null ? sum : 0.0;
    }

    @Override
    public long totalActions() {
        return actionRepository.count();
    }

    @Override
    public long totalOrganisationsValides() {
        return organisationRepository.countByValideTrue();
    }

    @Override
    public long totalParticipations() {
        return participationRepository.count();
    }
}