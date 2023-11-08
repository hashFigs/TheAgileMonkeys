package com.example.agile.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.Media;
import com.example.agile.repository.MediaRepository;

@Service
public class MediaService {

   @Value("${image.upload.path}") // Inject the configured path
    private String uploadPath;
   @Autowired
    private MediaRepository mediaRepository; // Create a MediaRepository to work with your database
    
    public Long uploadMedia(MultipartFile file) throws Exception {
        try {

            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
             
            // Generate a unique file name to avoid overwriting
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            // Build the absolute path to save the file
            String filePath = Paths.get(uploadPath, uniqueFileName).toString();

            // Save the file to the specified folder
            Files.write(Paths.get(filePath), file.getBytes());
            
            Media media = new Media();
            media.setMediaData(file.getBytes());
            media.setFileName(uniqueFileName);
            
            //TODO: images utilities
            media.setContentType("image");
            
            LocalDate localDate = LocalDate.now();
            Date uploadDate = Date.valueOf(localDate);
            
            media.setUploadDate(uploadDate);
            media.setHeight(20);
            media.setSize(400);
            media.setWidth(20);



            Media savedMedia = mediaRepository.save(media);
            return savedMedia.getId();
        } catch (IOException e) {
            // Handle file upload error
            throw new Exception("Failed to upload media", e);
        }
    }

    public Optional<Media> getMediaById(Long mediaId) {
        return mediaRepository.findById(mediaId);
    }

    // Implement methods for media management, such as deletion, updating metadata, etc. 
    
}
