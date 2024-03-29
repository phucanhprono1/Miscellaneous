package com.example.miscellaneous.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miscellaneous.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
}
