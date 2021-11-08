package com.ecs.latam.s3.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileS3 {

    private String path;
    private InputStream fileStream;
    private String name;
    private Integer maxAge;
    private boolean isUpload;
    private String fileBas64;
    private boolean found;
}
