package com.example.spring.services;

import com.example.spring.entities.Media;
import com.example.spring.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository repository;

    @Override
    public Media save(Media media) {
        return repository.save(media);
    }

    @Override
    public List<Media> getAll() {
        return repository.findAll();
    }

    @Override
    public Media getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media introuvable"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}