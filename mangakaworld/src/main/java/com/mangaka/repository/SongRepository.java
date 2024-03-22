package com.mangaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangaka.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
