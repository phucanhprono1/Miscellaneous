package com.example.miscellaneous.service;

import com.example.miscellaneous.model.Genre;
import com.example.miscellaneous.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    @Autowired
    private final GenreRepository genreRerepository;

    public List<Genre> getAll() {
        return genreRerepository.findAll();
    }

    public Genre getById(Long id) throws Exception {
        return genreRerepository.findById(id).orElseThrow(() -> new Exception("Genre not found"));
    }

    public Genre create(Genre genre) {
        return genreRerepository.save(genre);
    }

    public Genre update(Long id, Genre genre) throws Exception {
        Genre existing = getById(id);
        existing.setName(genre.getName());
        return genreRerepository.save(existing);
    }

    public void delete(Long id) throws Exception {
        genreRerepository.delete(getById(id));
    }
}
