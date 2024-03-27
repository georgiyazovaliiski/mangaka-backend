package com.mangaka.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mangaka.dto.MangaDTO;
import com.mangaka.service.MangaService;
import com.mangaka.util.FileVerificator;

@RestController
@RequestMapping("/manga")
public class MangaControllerImpl implements MangaController {
    @Autowired
    MangaService mangaService;

    @Autowired
    FileVerificator fileVerificator;

    @GetMapping("/{id}")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().build();
    }

    // TODO: Both CreateViaJPEGs and CreateViaPDF should be saving images in the
    // same way and naming.
    // Creating a Manga from JPEGs

    // Retrieve User ID and place their manga images there
    // e.g. manga.storage.location/userId/mangaId/chapterNo(this will come
    // later)/panel_no_1

    @PostMapping("/")
    public ResponseEntity<?> createViaJPEGs(@ModelAttribute MangaDTO mangaDTO,
            @RequestParam("pages") List<MultipartFile> pages) throws IOException {

        if (!fileVerificator.FileIsImage(pages)) {
            return ResponseEntity.badRequest().build();
        }

        mangaService.createManga(mangaDTO, pages);

        return ResponseEntity.ok().build();
    }

    // Creating a Manga from a PDF file
    @PostMapping("/pdf")
    public ResponseEntity<?> createViaPDF(@ModelAttribute MangaDTO mangaDTO,
            @RequestParam("pages") MultipartFile pages) throws IOException {
        if (!fileVerificator.FileIsPDF(pages)) {
            return ResponseEntity.badRequest().build();
        }

        mangaService.createManga(mangaDTO, pages);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute MangaDTO mangaDTO) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok().build();
    }
}
