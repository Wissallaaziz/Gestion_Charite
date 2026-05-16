package com.example.spring.repositories;

import com.example.spring.entities.ActionCharite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionChariteRepository extends JpaRepository<ActionCharite, Long> {
    long count();
}