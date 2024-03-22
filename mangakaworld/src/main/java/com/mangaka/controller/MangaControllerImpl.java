package com.mangaka.controller;

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

@RestController
@RequestMapping("/manga")
public class MangaControllerImpl implements MangaController {

    @GetMapping("/{id}")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@ModelAttribute MangaDTO mangaDTO, @RequestParam("pages") MultipartFile pages) {
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
