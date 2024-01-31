package com.example.miscellaneous.controller;

import com.example.miscellaneous.model.Song;
import com.example.miscellaneous.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("artist") String artist) {
        // Save the file to a storage location and save the song details to the database
        // Don't forget to set the file path in the Song entity
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setFilePath("/path/to/your/storage/" + file.getOriginalFilename());
        songRepository.save(song);

        return ResponseEntity.ok("Song uploaded successfully!");
    }
    @GetMapping("/stream/{id}")
    public ResponseEntity<InputStreamResource> streamSong(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            try {
                File file = new File(song.getFilePath());
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(resource);
            } catch (IOException e) {
                // Handle exceptions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}