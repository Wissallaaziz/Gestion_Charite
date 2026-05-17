package com.example.spring.services;

import com.example.spring.entities.Admin;
import com.example.spring.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin login(String email, String password) {

        Optional<Admin> admin = adminRepository.findByEmail(email);

        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return admin.get();
        }

        return null;
    }
}