package com.mangaka.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangaka.dto.MangaDTO;
import com.mangaka.dto.PageDTO;
import com.mangaka.service.MangaService;
import com.mangaka.service.PageService;
import com.mangaka.util.FileVerificator;

@RestController
@RequestMapping("/manga")
public class MangaControllerImpl implements MangaController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MangaService mangaService;

    @Autowired
    PageService pageService;

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
        MangaDTO mangaDTO = objectMapper.readValue(mangaDTOJson, MangaDTO.class);

        MangaDTO resultingDTO = mangaService.createManga(mangaDTO, pages);

        return ResponseEntity.ok().body(resultingDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute String mangaDTOJson)
            throws JsonMappingException, JsonProcessingException {
        MangaDTO mangaDTO = objectMapper.readValue(mangaDTOJson, MangaDTO.class);

        MangaDTO resultingDTO = mangaService.updateManga(id, mangaDTO);
        return ResponseEntity.ok().body(resultingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mangaService.deleteManga(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/songs")
    public ResponseEntity<?> addSongsToMangaPages(
            @ModelAttribute("dto") String pageSongDTOsJson,
            @RequestParam("songs") List<MultipartFile> songs) throws IOException {
        List<PageDTO> pageDTOs = objectMapper.readValue(pageSongDTOsJson, new TypeReference<List<PageDTO>>() {
        });

        if (pageDTOs.size() != songs.size()) {
            return ResponseEntity.badRequest().build();
        }

        for (int i = 0; i < pageDTOs.size(); i++) {
            PageDTO pageDTO = pageDTOs.get(i);
            MultipartFile song = songs.get(i);
            pageService.updateSong(pageDTO.getPageId(), pageDTO.getSongName(), song);
        }

        return ResponseEntity.ok().build();
    }
}
