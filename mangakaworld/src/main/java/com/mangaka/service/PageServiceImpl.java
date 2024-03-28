package com.mangaka.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.mangaka.model.Page;
import com.mangaka.model.Song;
import com.mangaka.repository.PageRepository;
import com.mangaka.util.FileSaver;

public class PageServiceImpl implements PageService {
    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private FileSaver fileSaver;

    @Override
    @Transactional
    public String updateSong(Long id, String songName, MultipartFile songFile) throws IOException {
        fileSaver.saveSong(songFile);
        Page existingPage = pageRepository.findById(id).orElse(null);
        Song song = existingPage.getSong();
        song.setName(songName);
        existingPage.setSong(song);
        Page newPage = pageRepository.save(existingPage);
        return newPage.getSong().getName();
    }

}
