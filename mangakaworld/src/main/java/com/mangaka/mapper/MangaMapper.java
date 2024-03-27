package com.mangaka.mapper;

import com.mangaka.dto.MangaDTO;
import com.mangaka.model.Manga;

public interface MangaMapper {
    MangaDTO mangaToMangaDTO(Manga m);

    Manga mangaDTOtoManga(MangaDTO m);
}
