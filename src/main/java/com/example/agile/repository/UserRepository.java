package com.example.agile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agile.models.ApplicationUser;
import com.example.agile.models.Media;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer>{
    Optional<ApplicationUser> findByUsername(String username);
   // Optional<ApplicationUser> findById(Long Id);
    Optional<ApplicationUser> findByUserId(Long UserId);
    void deleteByUserId(Long userId);
    
}
