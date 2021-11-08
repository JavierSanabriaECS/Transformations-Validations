package com.ecs.latam.s3.adapter.in.web;



import com.ecs.latam.s3.application.port.in.FileS3;
import com.ecs.latam.s3.application.port.in.S3Management;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("s3")
public class S3ManagementController {

    private final S3Management s3Management;

    @PostMapping
    public FileS3 uploadFile(@RequestParam String path,
                             @RequestParam MultipartFile file,
                             @RequestParam(required = false, defaultValue = "31536000") Integer maxAge) {
        FileS3 fileS3 = new FileS3();
        try {
            fileS3 = FileS3.builder().path(path).fileStream(file.getInputStream()).name(file.getOriginalFilename()).maxAge(maxAge).build();
            return s3Management.uploadFile(fileS3);
        } catch (IOException e) {
            fileS3.setUpload(false);
            return fileS3;
        }
    }

    @GetMapping
    public FileS3 downloadFile(@RequestParam String path,
                               @RequestParam String name) {
        FileS3 fileS3 = FileS3.builder().path(path).name(name).build();
        return s3Management.downloadFile(fileS3);
    }
}