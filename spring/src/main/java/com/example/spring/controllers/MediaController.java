package com.example.spring.controllers;

import com.example.spring.entities.Media;
import com.example.spring.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService service;

    @PostMapping
    public Media create(@RequestBody Media media) {
        return service.save(media);
    }

    @GetMapping
    public List<Media> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Media getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}