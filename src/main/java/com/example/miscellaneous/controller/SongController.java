package com.example.miscellaneous.controller;

import com.example.miscellaneous.dto.SongDto;
import com.example.miscellaneous.model.Song;
import com.example.miscellaneous.repository.SongRepository;
import com.example.miscellaneous.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
public class SongController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("artist") String artist,@Valid @RequestBody  SongDto songDto) throws IOException {
        // Save the file to a storage location and save the song details to the database
        String filePath = uploadPath + File.separator + file.getOriginalFilename();
        saveFile(file, filePath);
        // Don't forget to set the file path in the Song entity
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setFilePath(uploadPath + file.getOriginalFilename());
        song.setGenre(songDto.getGenre());
        song.setCreatedAt(LocalDateTime.now());
//        songRepository.save(song);
        songService.saveSong(song, file);
        return ResponseEntity.ok("Song uploaded successfully!");
    }
    @GetMapping("/allSongs")
    public ResponseEntity<?> getAllSongs(){
        return ResponseEntity.ok(songRepository.findAll());
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
                String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString());
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

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
    private void saveFile(MultipartFile file, String filePath) throws IOException {
        // Tạo một luồng đầu ra để lưu file
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(file.getBytes());
        }
    }
}
