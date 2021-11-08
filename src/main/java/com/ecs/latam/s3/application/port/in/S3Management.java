package com.ecs.latam.s3.application.port.in;

public interface S3Management {

    FileS3 uploadFile(FileS3 file);

    FileS3 downloadFile(FileS3 fileS3);

}
