package com.example.agile.services;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.Media;
import com.example.agile.repository.MediaRepository;

@Service
public class MediaService {

  // @Value("${image.upload.path}") // Inject the configured path
  //   private String uploadPath;
   @Autowired
    private MediaRepository mediaRepository; // Create a MediaRepository to work with your database
    @Autowired
    private S3FileUploadService s3FileUploadService;
    
    public Media uploadMedia(MultipartFile file) throws Exception {
        try {
            
             Media uploadedMedia = s3FileUploadService.uploadFile(file);
             uploadedMedia.setCreatedAt(Instant.now());

             Media savedMedia = mediaRepository.save(uploadedMedia);
            
             uploadedMedia.setId(savedMedia.getId());
         
            return uploadedMedia;
 
        } catch (IOException e) {
            // Handle file upload error
            throw new Exception("Failed to upload media", e);
        }
    }

    public Optional<Media> getMediaById(Long mediaId) {
        return mediaRepository.findById(mediaId);
    }
    
}
