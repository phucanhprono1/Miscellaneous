package com.example.miscellaneous.service;

import com.example.miscellaneous.model.Song;
import com.example.miscellaneous.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    public String saveSong(Song song, MultipartFile file) throws IOException {

        saveFile(file,song.getFilePath());
        songRepository.save(song);
        return "Song uploaded successfully!";
    }
    private void saveFile(MultipartFile file, String filePath) throws IOException {
        // Tạo một luồng đầu ra để lưu file
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(file.getBytes());
        }
    }
}
