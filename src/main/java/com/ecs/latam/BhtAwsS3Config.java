package com.ecs.latam;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BhtAwsS3Config {

    @Value("${s3.config.url}")
    private String s3ConfigUrl;
    @Value("${s3.config.region}")
    private String s3ConfigRegion;
    @Value("${s3.config.access-key-id}")
    private String s3ConfigAccessKeyId;
    @Value("${s3.config.secret-key-id}")
    private String s3ConfigSecretKeyId;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials credentials = new BasicAWSCredentials(s3ConfigAccessKeyId, s3ConfigSecretKeyId);
        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3ConfigUrl, s3ConfigRegion))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}

