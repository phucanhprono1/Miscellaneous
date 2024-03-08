package com.example.miscellaneous.dto;

import lombok.Data;
import org.springframework.core.io.InputStreamResource;

@Data
public class SongDto {
    private Long id;
    private String name;
    private String artist;
    private String genre;
    private String releaseDate;
    private String language;
    private String album;
    private InputStreamResource file;
}
