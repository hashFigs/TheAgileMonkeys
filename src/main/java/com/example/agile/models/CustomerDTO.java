package com.example.agile.models;

public class CustomerDTO {

    private Long id ;
    private String name;
    private String surname;
    private String lastUpdatedBy;
    private Long mediaId;
    private String photoUrl;

    

    public CustomerDTO(){
        super();
    }

    public CustomerDTO(Media media){
        this.photoUrl = media.getFileName();
    }

    // Constructor with all fields
    public CustomerDTO(Long id, String name, String surname, String lastUpdatedBy, Long mediaId, String photoUrl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastUpdatedBy = lastUpdatedBy;
        this.mediaId = mediaId;
        this.photoUrl = photoUrl;
    }
   
     // Getter and Setter methods for id
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter and Setter methods for lastUpdatedBy
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // Getter and Setter methods for mediaId
    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    // Getter and Setter methods for photoUrl
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
}
