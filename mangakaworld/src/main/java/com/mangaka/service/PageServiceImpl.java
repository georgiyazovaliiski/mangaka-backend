package com.mangaka.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangaka.model.Page;
import com.mangaka.model.Song;
import com.mangaka.repository.PageRepository;
import com.mangaka.repository.SongRepository;
import com.mangaka.util.FileSaver;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private FileSaver fileSaver;

    @Override
    @Transactional
    public String updateSong(Long id, String songName, MultipartFile songFile) throws IOException {
        fileSaver.saveSong(songFile);
        Page existingPage = pageRepository.findById(id).orElse(null);

        Song song = existingPage.getSong();
        if (song == null) {
            song = new Song();
        }

        song.setName(songName);
        existingPage.setSong(song);

        songRepository.save(song);

        Page updatedPage = pageRepository.save(existingPage);
        return updatedPage.getSong().getName();
    }

}
