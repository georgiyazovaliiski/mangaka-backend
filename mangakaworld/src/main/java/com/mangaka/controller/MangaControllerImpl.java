package com.mangaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(mangaService.getMangaById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(mangaService.getAllMangas());
    }

    // TODO: Both CreateViaJPEGs and CreateViaPDF should be saving images in the
    // same way and naming.
    // Creating a Manga from JPEGs

    // Retrieve User ID and place their manga images there
    // e.g. manga.storage.location/userId/mangaId/chapterNo(this will come
    // later)/panel_no_1

    @PostMapping("/")
    public ResponseEntity<?> createViaJPEGs(
            @RequestParam("dto") String mangaDTOJson,
            @RequestParam("pages") List<MultipartFile> pages) throws Exception {

        if (!fileVerificator.FileIsImage(pages)) {
            return ResponseEntity.badRequest().build();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        MangaDTO mangaDTO = objectMapper.readValue(mangaDTOJson, MangaDTO.class);

        MangaDTO resultingDTO = mangaService.createManga(mangaDTO, pages);

        return ResponseEntity.ok().body(resultingDTO);
    }

    // Creating a Manga from a PDF file
    @PostMapping("/pdf")
    public ResponseEntity<?> createViaPDF(
            @RequestParam("dto") String mangaDTOJson,
            @RequestParam("pages") MultipartFile pages) throws Exception {
        if (!fileVerificator.FileIsPDF(pages)) {
            return ResponseEntity.badRequest().build();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        MangaDTO mangaDTO = objectMapper.readValue(mangaDTOJson, MangaDTO.class);

        MangaDTO resultingDTO = mangaService.createManga(mangaDTO, pages);

        return ResponseEntity.ok().body(resultingDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute MangaDTO mangaDTO) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mangaService.deleteManga(id);
        return ResponseEntity.ok().build();
    }
}
