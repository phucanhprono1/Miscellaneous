package com.example.miscellaneous.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miscellaneous.model.Singer;
import com.example.miscellaneous.repository.SingerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SingerService {
    @Autowired
    private  SingerRepository singerRepository;
    public void addSinger(Singer singer) {
        singerRepository.save(singer);
    }

    public void deleteSinger(Singer singer) {
        // Delete singer from the database
        singerRepository.delete(singer);    
    }

    public void updateSinger(Singer singer) {
        // Update singer in the database
        singerRepository.save(singer);
    }

    public List<Singer> getAllSingers() {
        // Get all singers from the database
        return singerRepository.findAll();
    }

    public Singer getSingerById(Long id) {
        // Get singer from the database by id
        try {
            return singerRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    

    
} 
