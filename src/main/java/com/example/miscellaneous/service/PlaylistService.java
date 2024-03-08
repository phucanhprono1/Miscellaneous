package com.example.miscellaneous.service;

import com.example.miscellaneous.model.Playlist;
import com.example.miscellaneous.model.Song;
import com.example.miscellaneous.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    public void addPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }
    public void deletePlaylist(Playlist playlist) {
        // Delete playlist from the database
        playlistRepository.delete(playlist);
    }
    public void updatePlaylist(Playlist playlist) {
        // Update playlist in the database
        playlistRepository.save(playlist);
    }
    public Playlist getPlaylistById(Long id) {
        // Get playlist from the database by id
        try {
            return playlistRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
    public void addSongToPlaylist(Playlist playlist, Song song) {
        // Add song to playlist
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

}
