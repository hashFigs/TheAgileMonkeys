package com.example.agile.models;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] mediaData;

    private String fileName;
    private String contentType;
    private Date uploadDate;
    private int height;
    private int width;
    private long mediaSize;

    // Constructors

    public Media() {
        // Default constructor
    }

    public Media(byte[] mediaData, String fileName, String contentType, Date uploadDate, int height, int width, long mediaSize) {
        this.mediaData = mediaData;
        this.fileName = fileName;
        this.contentType = contentType;
        this.uploadDate = uploadDate;
        this.height = height;
        this.width = width;
        this.mediaSize = mediaSize;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public long getSize() {
        return mediaSize;
    }

    public void setSize(long mediaSize) {
        this.mediaSize = mediaSize;
    }



    // Additional fields and methods

    // Customize this class with any additional fields and methods as needed.
}