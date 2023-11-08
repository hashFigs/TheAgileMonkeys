package com.example.agile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agile.models.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
        Optional<Media> findById(Long Id);

}
