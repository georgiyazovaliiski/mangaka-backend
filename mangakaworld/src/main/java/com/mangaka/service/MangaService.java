package com.mangaka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangaka.model.Manga;
import com.mangaka.repository.MangaRepository;

@Service
public class MangaService {

    private final MangaRepository MangaRepository;

    @Autowired
    public MangaService(MangaRepository MangaRepository) {
        this.MangaRepository = MangaRepository;
    }

    // Create operation
    public Manga createManga(Manga Manga) {
        return MangaRepository.save(Manga);
    }

    // Read operations
    public Manga getMangaById(Long id) {
        return MangaRepository.findById(id).orElse(null);
    }

    public List<Manga> getAllMangas() {
        return MangaRepository.findAll();
    }

    // Update operation
    public Manga updateManga(Long id, Manga updatedManga) {
        Manga existingManga = MangaRepository.findById(id).orElse(null);
        if (existingManga == null) {
            // Handle error or throw exception if Manga not found
            return null;
        }

        // Update the existing Manga with the new data

        // Update other fields as needed

        return MangaRepository.save(existingManga);
    }

    // Delete operation
    public void deleteManga(Long id) {
        MangaRepository.deleteById(id);
    }
}
