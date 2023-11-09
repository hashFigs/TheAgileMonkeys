package com.example.agile.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    // You can customize the photo field based on your requirements.
    // For simplicity, we'll use a String to store the photo URL.
    private Long photoId;

    private String lastUpdated;

    // Constructors, getters, and setters

    public Customer() {
        // Default constructor
    }

    public Customer(String name, String surname, Long photoId, String lastUpdated) {
        this.name = name;
        this.surname = surname;
        this.photoId = photoId;
        this.lastUpdated = lastUpdated;
    }

    // Getters and setters for the fields
    
    public String getLastUpdated(){
        return this.lastUpdated;
    }

    public void setLastUpdated(String lastUpdated){
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPhoto() {
        return photoId;
    }

    public void setPhoto(Long photoId) {
        this.photoId = photoId;
    }
}