package com.mangaka.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangaka.dto.MangaDTO;

public interface MangaService {
    MangaDTO createManga(MangaDTO Manga, MultipartFile file);

    MangaDTO createManga(MangaDTO Manga, List<MultipartFile> files);

    MangaDTO getMangaById(Long id);

    List<MangaDTO> getAllMangas();

    MangaDTO updateManga(Long id, MangaDTO updatedManga);

    void deleteManga(Long id);
}
