package com.example.spring.controllers;

import com.example.spring.entities.ActionCharite;
import com.example.spring.entities.Utilisateur;
import com.example.spring.services.ActionChariteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
@CrossOrigin("*")
public class ActionChariteApiController {

    @Autowired
    private ActionChariteService actionService;

    @GetMapping
    public List<ActionCharite> getAll() {
        return actionService.getAll();
    }

    @GetMapping("/{id}")
    public ActionCharite getById(@PathVariable Long id) {
        return actionService.getById(id);
    }

    @PostMapping
    public ActionCharite create(@RequestBody ActionCharite action, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Connexion requise");
        }
        if (!"ORGANISATION".equalsIgnoreCase(currentUser.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Seule une organisation peut creer une campagne");
        }
        return actionService.save(action);
    }

    @PutMapping("/{id}")
    public ActionCharite update(@PathVariable Long id, @RequestBody ActionCharite action) {
        return actionService.update(id, action);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        actionService.delete(id);
    }
}
