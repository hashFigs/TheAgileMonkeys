package com.example.agile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agile.models.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long>{
    boolean existsById(Long userId);

    Optional<ApplicationUser> findByUsername(String username);
   // Optional<ApplicationUser> findById(Long Id);
    Optional<ApplicationUser> findByUserId(Long userId);
    void deleteByUserId(Long userId);
    
}
