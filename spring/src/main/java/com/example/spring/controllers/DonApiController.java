package com.example.spring.controllers;

import com.example.spring.entities.Don;
import com.example.spring.services.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dons")
@CrossOrigin("*")
public class DonApiController {

    @Autowired
    private DonService donService;

    @GetMapping
    public List<Don> getAll() {
        return donService.getAll();
    }

    @GetMapping("/{id}")
    public Don getById(@PathVariable Long id) {
        return donService.getById(id);
    }

    @PostMapping
    public Don create(@RequestBody Don don) {
        if (don.getDate() == null) {
            don.setDate(new Date());
        }
        return donService.save(don);
    }

    @PutMapping("/{id}")
    public Don update(@PathVariable Long id, @RequestBody Don payload) {
        Don don = donService.getById(id);
        don.setMontant(payload.getMontant());
        don.setDate(payload.getDate() != null ? payload.getDate() : don.getDate());
        don.setMethodePaiement(payload.getMethodePaiement());
        don.setUtilisateur(payload.getUtilisateur());
        don.setActionCharite(payload.getActionCharite());
        return donService.save(don);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donService.delete(id);
    }
}
