package com.example.agile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.Media;
import com.example.agile.services.MediaService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    //@GetMapping
    //public List<Media> getAllMedia() {
    //    return mediaService.getAllMedia();
   // }

    @GetMapping("/{id}")
    public Optional<Media> getMediaById(@PathVariable Long id) {
        return mediaService.getMediaById(id);
    }

    @PostMapping("/upload")
    public Long uploadMedia(@RequestParam("file") MultipartFile file) throws Exception {
        return mediaService.uploadMedia(file);
    }

    //@PutMapping("/{id}")
    //public Media updateMedia(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
    //    return mediaService.updateMedia(id, file);
   // }

    //@DeleteMapping("/{id}")
    //public void deleteMedia(@PathVariable Long id) {
    //    mediaService.deleteMedia(id);
   // }
}