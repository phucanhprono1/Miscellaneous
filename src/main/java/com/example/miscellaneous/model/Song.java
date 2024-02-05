package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_song")
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private String releaseDate;
    private String language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_album_id")
    private Album album; // Album to which the song belongs
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbl_playlist_id")
    private Playlist playlist; // Playlist to which the song belongs
    private String filePath; // Path to the stored audio file

    // Getters and setters
}