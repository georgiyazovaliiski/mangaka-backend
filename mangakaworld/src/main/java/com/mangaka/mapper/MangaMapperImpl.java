package com.mangaka.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mangaka.dto.MangaDTO;
import com.mangaka.model.Manga;
import com.mangaka.model.Page;

@Component
public class MangaMapperImpl implements MangaMapper {
    public MangaDTO mangaToMangaDTO(Manga m) {
        List<Page> pages = m.getPages();

        return new MangaDTO();
    };

    public Manga mangaDTOtoManga(MangaDTO m) {
        return new Manga();
    };
}
