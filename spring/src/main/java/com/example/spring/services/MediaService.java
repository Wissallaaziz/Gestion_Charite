package com.example.spring.services;

import com.example.spring.entities.Media;

import java.util.List;

public interface MediaService {

    Media save(Media media);

    List<Media> getAll();

    Media getById(Long id);

    void delete(Long id);
}