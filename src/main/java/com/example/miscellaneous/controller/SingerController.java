package com.example.miscellaneous.controller;

import com.example.miscellaneous.model.Singer;
import com.example.miscellaneous.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;
    @GetMapping("/all")
    public ResponseEntity<List<Singer>> getAllSingers() {
        return ResponseEntity.ok(singerService.getAllSingers());
    }
    @PostMapping("/add")
    public ResponseEntity<String> addSinger(Singer singer) {
        singerService.addSinger(singer);
        return ResponseEntity.ok("Singer added successfully!");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteSinger(Singer singer) {
        singerService.deleteSinger(singer);
        return ResponseEntity.ok("Singer deleted successfully!");
    }
}
