package com.example.agile.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.UUID;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.agile.models.Media;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3FileUploadService {
     @Autowired
    private S3Client s3Client;

    private static final String BUCKET_NAME = "agilemonkey"; // Replace with your S3 bucket name

    public Media uploadFile(MultipartFile file) throws IOException {
        // Generate a unique key for the file (you might want to customize this based on your application logic)
        String key = "customersPics/" + UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Upload the file to S3
         try (InputStream inputStream = file.getInputStream()) {
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(key)
                    .build(),
                    RequestBody.fromInputStream(inputStream, file.getSize()));
        }

        // Return the S3 URL or key for further use (e.g., storing in a database)
        Media returnMedia = new Media();
        returnMedia.setFileName("https://" + BUCKET_NAME + ".s3.amazonaws.com/" + key);
        returnMedia.setContentType("image");
        returnMedia.setHeight(200);
        returnMedia.setWidth(350);
        //returnMedia.setUploadDate(Date.from(Instant.now()));


        
        return returnMedia;
    }
}
