package com.ecs.latam.s3.application.service;

import com.ecs.latam.ValidationMT940;
import com.ecs.latam.commons.Util.Buckets;
import com.ecs.latam.kafka.application.service.ConsumerMessageKafkaService;
import com.ecs.latam.s3.application.port.in.FileS3;
import com.ecs.latam.s3.application.port.in.S3Management;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class S3ManagementService implements S3Management {

    private final AmazonS3 amazonS3;

    @Override
    public FileS3 uploadFile(FileS3 file) {
        file.setUpload(upload(Buckets.PRINCIPAL_BUCKET.getBucketName(), file.getPath(),
                file.getName(), Optional.empty(), file.getFileStream()));
        return file;
    }

    @Override
    public FileS3 downloadFile(FileS3 file) {
        try {
            file.setFileBas64(Base64.getEncoder().encodeToString(download(file.getPath(), file.getName())));
            file.setFound(true);
        } catch (Exception e) {
            System.out.println("Error " + e);
            file.setFound(false);
        }
        return file;
    }

    private boolean upload(String bucket, String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
        System.out.println("SftpManagement :: upload :: upload file " + fileName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            if (createBucket(bucket)) {
                amazonS3.putObject("/" + bucket + path, fileName, inputStream, objectMetadata);
                return true;
            }
            return false;
        } catch (AmazonServiceException e) {
            System.out.println("Failed to upload the file"+ e);
            return false;
        }
    }

    private byte[] download(String path, String fileName) {
        System.out.println("SftpManagement :: download :: download file " + fileName);
        try {
            S3Object object = amazonS3.getObject(path, fileName);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

    private boolean createBucket(String bucketName) {
        try {
            /*if (!amazonS3.doesBucketExistV2(bucketName)) {
                Bucket bucket = amazonS3.createBucket(bucketName);
                System.out.println(bucket.getCreationDate());
            }*/
            return true;
        } catch (Exception e) {
            System.out.println("Failed to create bucket" + e);
        }
        return true;
    }
}

