package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

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
    private String filePath; // Path to the stored audio file
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigInteger viewsCount;

    // Getters and setters
}