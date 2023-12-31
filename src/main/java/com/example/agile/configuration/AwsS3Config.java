package com.example.agile.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;



@Configuration
public class AwsS3Config {
    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
        .region(Region.US_EAST_1)  
        .credentialsProvider(awsCredentialsProvider())
        .build();
    }

     @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        // Replace "your-access-key-id" and "your-secret-access-key" with your actual AWS access key and secret key.
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretKey);
        return StaticCredentialsProvider.create(awsCredentials);
    }
}









