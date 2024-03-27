package com.mangaka.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangaka.dto.MangaDTO;
import com.mangaka.mapper.MangaMapper;
import com.mangaka.model.Manga;
import com.mangaka.model.Page;
import com.mangaka.repository.MangaRepository;
import com.mangaka.repository.PageRepository;
import com.mangaka.repository.SongRepository;
import com.mangaka.util.FileSaver;

@Service
public class MangaServiceImpl implements MangaService {
    @Autowired
    private MangaMapper mangaMapper;

    @Autowired
    private FileSaver fileSaver;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private SongRepository songRepository;

    // Create operation
    public MangaDTO createManga(MangaDTO Manga, MultipartFile file) throws IOException {
        Manga savingManga = mangaMapper.mangaDTOtoManga(Manga);

        List<String> pageNames = fileSaver.savePdfAsJpgs(file);

        for (String pageName : pageNames) {
            Page page = new Page(savingManga, pageName);
            savingManga.getPages().add(page);
        }

        return mangaMapper.mangaToMangaDTO(mangaRepository.save(savingManga));
    }

    // Create operation
    public MangaDTO createManga(MangaDTO Manga, List<MultipartFile> files) throws IOException {
        Manga savingManga = mangaMapper.mangaDTOtoManga(Manga);

        List<String> pageNames = fileSaver.saveJpgs(files);

        for (String pageName : pageNames) {
            Page page = new Page(savingManga, pageName);
            savingManga.getPages().add(page);
        }

        return mangaMapper.mangaToMangaDTO(mangaRepository.save(savingManga));
    }

    // Read operations
    public MangaDTO getMangaById(Long id) {
        return mangaMapper.mangaToMangaDTO(mangaRepository.findById(id).orElse(null));
    }

    public List<MangaDTO> getAllMangas() {
        // return mangaRepository.findAll();
        return List.of();
    }

    // Update operation
    public MangaDTO updateManga(Long id, MangaDTO updatedManga) {
        Manga existingManga = mangaRepository.findById(id).orElse(null);
        if (existingManga == null) {
            // Handle error or throw exception if Manga not found
            return null;
        }

        // Update the existing Manga with the new data

        // Update other fields as needed

        return mangaMapper.mangaToMangaDTO(mangaRepository.save(existingManga));
    }

    // Delete operation
    public void deleteManga(Long id) {
        mangaRepository.deleteById(id);
    }
}