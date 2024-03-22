package com.mangaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangaka.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
}
