package com.shivamingale.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;
    private final String region;
    private final String accessKey;
    private final String secretKey;

    public S3Service(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucketName, @Value("${aws.s3.region}") String region, @Value("${aws.s3.access-key}") String accessKey, @Value("${aws.s3.secret-key}") String secretKey) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.region = region;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    // Upload a file to S3
    public String uploadFile(String directory, String fileName, InputStream inputStream, long contentLength) {
        String key = directory != null ? directory + "/" + fileName : fileName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, contentLength));

        return getFileUrl(key);
    }

    // Download a file from S3
    public void downloadFile(String directory, String fileName, String downloadPath) {
        String key = directory != null ? directory + "/" + fileName : fileName;

        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();

        s3Client.getObject(getObjectRequest, Paths.get(downloadPath));
    }

    // List all files in a directory
    public List<String> listFiles(String directory) {
        String prefix = directory != null ? directory + "/" : "";

        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder().bucket(bucketName).prefix(prefix).build();

        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);

        return listObjectsResponse.contents().stream().map(S3Object::key).collect(Collectors.toList());
    }

    // Generate a presigned URL for secure access
    public URL generatePresignedUrl(String directory, String fileName, int expirationInMinutes) {
        String key = directory != null ? directory + "/" + fileName : fileName;

        S3Presigner presigner = S3Presigner.builder().region(Region.of(region)).credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))).build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();

        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(b -> b.getObjectRequest(getObjectRequest).signatureDuration(Duration.ofMinutes(expirationInMinutes)));

        presigner.close(); // Close the presigner to avoid resource leaks
        return presignedRequest.url();
    }

    // Get a public URL for a file
    public String getFileUrl(String key) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }

    // Delete a file
    public void deleteFile(String directory, String fileName) {
        String key = directory != null ? directory + "/" + fileName : fileName;

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(key).build();

        s3Client.deleteObject(deleteObjectRequest);
    }
}
