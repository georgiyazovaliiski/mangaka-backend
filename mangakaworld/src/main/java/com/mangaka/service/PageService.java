package com.mangaka.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PageService {
    String updateSong(Long id, String songName, MultipartFile songFile) throws IOException;
}
