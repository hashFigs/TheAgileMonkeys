package com.example.agile.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;



@Configuration
public class AwsS3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
        .region(Region.US_EAST_1)  // Replace with your AWS region, e.g., Region.US_EAST_1
        .credentialsProvider(awsCredentialsProvider())
        .build();
    }

     @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        // Replace "your-access-key-id" and "your-secret-access-key" with your actual AWS access key and secret key.
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("AKIAXT5HOXFXC32UNLPL", "ggX620GXxYoHrn3fzs/N2RaTj1vtD75yuHtf6AfY");
        return StaticCredentialsProvider.create(awsCredentials);
    }
}

