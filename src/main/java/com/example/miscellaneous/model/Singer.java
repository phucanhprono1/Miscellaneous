package com.example.miscellaneous.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dob;
    private String country;
    private String avatar;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "tbl_singer_song",
            joinColumns = @JoinColumn(name = "tbl_singer_id"),
            inverseJoinColumns = @JoinColumn(name = "tbl_song_id"))
    private List<Song> songs;
    @ManyToMany
    @JoinTable(
            name = "tbl_singer_genre",
            joinColumns = @JoinColumn(name = "tbl_singer_id"),
            inverseJoinColumns = @JoinColumn(name = "tbl_genre_id"))
    private List<Genre> genres;
}
