package com.example.miscellaneous.repository;

import com.example.miscellaneous.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}

