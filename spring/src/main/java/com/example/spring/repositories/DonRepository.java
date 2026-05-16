package com.example.spring.repositories;

import com.example.spring.entities.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonRepository extends JpaRepository<Don, Long> {

    @Query("SELECT SUM(d.montant) FROM Don d")
    Double totalDons();

    @Query("""
            SELECT d.actionCharite.id, COALESCE(SUM(d.montant), 0)
            FROM Don d
            WHERE d.utilisateur.id = :userId
            GROUP BY d.actionCharite.id
            """)
    List<Object[]> sumDonationsByActionForUser(Long userId);
}