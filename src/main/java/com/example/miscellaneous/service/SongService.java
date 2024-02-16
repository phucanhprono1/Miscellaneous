package com.example.miscellaneous.service;

import com.example.miscellaneous.model.Song;
import com.example.miscellaneous.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }
    public Song getSongById(Long id){
        return songRepository.findById(id).orElse(null);
    }
    public Song saveSong(Song song){
        return songRepository.save(song);
    }

}
