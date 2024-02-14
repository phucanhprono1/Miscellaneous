package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String artist;
    private String releaseDate;
    private String cover;
    private String description;
    private String label;
    private String producer;
    private String language;
    private String type;
    @ManyToMany
    @JoinTable(
            name = "tbl_album_genre",
            joinColumns = @JoinColumn(name = "tbl_album_id"),
            inverseJoinColumns = @JoinColumn(name = "tbl_genre_id"))
    private List<Genre> genres;

}
