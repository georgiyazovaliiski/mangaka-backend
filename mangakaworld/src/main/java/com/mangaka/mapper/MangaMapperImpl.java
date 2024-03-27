package com.mangaka.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mangaka.dto.MangaDTO;
import com.mangaka.model.Manga;
import com.mangaka.model.Page;

@Component
public class MangaMapperImpl implements MangaMapper {
    public MangaDTO mangaToMangaDTO(Manga m) {
        MangaDTO dto = new MangaDTO();
        dto.setMangaka(m.getMangaka());
        dto.setDescription(m.getDescription());
        dto.setName(m.getName());

        List<Page> pages = m.getPages();
        List<String> pagesDTO = new ArrayList<>();

        for (Page page : pages) {
            pagesDTO.add(page.getName());
        }

        dto.setPagesFileNames(pagesDTO);

        return dto;
    };

    public Manga mangaDTOtoManga(MangaDTO m) {
        Manga manga = new Manga();
        manga.setDescription(m.getDescription());
        manga.setMangaka(m.getMangaka());
        manga.setName(m.getName());

        return manga;
    };
}
