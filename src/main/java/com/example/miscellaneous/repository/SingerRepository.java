package com.example.miscellaneous.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miscellaneous.model.Singer;


@Repository
public interface SingerRepository extends JpaRepository<Singer, Long>{
    
}
