package com.mangaka.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mangaka.dto.MangaDTO;

public interface MangaController {
    ResponseEntity<?> get();

    ResponseEntity<?> getAll();

    ResponseEntity<?> create(@ModelAttribute MangaDTO mangaDTO, @RequestParam("pages") List<MultipartFile> pages);

    // Updates the general information of a manga piece
    ResponseEntity<?> update(@ModelAttribute MangaDTO mangaDTO);

    // Adds a song to a page of the manga
    // ResponseEntity<?> addSongToPage(@ModelAttribute MangaDTO mangaDTO,
    // @RequestParam("pages") MultipartFile pages);

    ResponseEntity<?> delete();
}
