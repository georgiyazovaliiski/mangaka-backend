package com.mangaka.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MangaController {
        ResponseEntity<?> get(@PathVariable Long id);

        ResponseEntity<?> getAll();

        // Accept either multiple JPEGs or a single PDF. If a PDF, split it to multiple
        // images
        ResponseEntity<?> createViaJPEGs(
                        @RequestParam("dto") String mangaDTO,
                        @RequestParam("pages") List<MultipartFile> pages)
                        throws Exception;

        ResponseEntity<?> createViaPDF(
                        @RequestParam("dto") String mangaDTO,
                        @RequestParam("pages") MultipartFile pages)
                        throws Exception;

        // Updates the general information of a manga piece
        ResponseEntity<?> update(
                        @PathVariable Long id,
                        @ModelAttribute String mangaDTO)
                        throws JsonMappingException, JsonProcessingException;

        // Adds a song to a page of the manga
        // ResponseEntity<?> addSongToPage(@ModelAttribute MangaDTO mangaDTO,
        // @RequestParam("pages") MultipartFile pages);

        ResponseEntity<?> delete(@PathVariable Long id);

        ResponseEntity<?> addSongsToMangaPages(
                        @ModelAttribute("dto") String pageSongDTOsJson,
                        @RequestParam("songs") List<MultipartFile> songs)
                        throws JsonMappingException, JsonProcessingException, IOException;
}
