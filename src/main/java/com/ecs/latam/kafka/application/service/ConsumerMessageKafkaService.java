package com.ecs.latam.kafka.application.service;

import com.ecs.latam.TransformMT940toXML;
import com.ecs.latam.ValidationMT940;
import com.ecs.latam.commons.Util.Buckets;
import com.ecs.latam.commons.schemas.TransformationObject;
import com.ecs.latam.kafka.domain.DataTransformation;
import com.ecs.latam.s3.application.port.in.FileS3;
import com.ecs.latam.s3.application.port.in.S3Management;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;

@Service

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ConsumerMessageKafkaService {

    private final S3Management s3Management;

    @KafkaListener(topics = "Transformation1", groupId = "FIRST_GROUP")
    public void tranformation(ConsumerRecord<String, TransformationObject> transformationObject) throws DatatypeConfigurationException, ParseException {
        System.out.println("<<<<START TRANSFORMATION>>>>");
        System.out.println(transformationObject.value());
        DataTransformation dataTransformation = new Gson().fromJson(String.valueOf(transformationObject.value()), DataTransformation.class);
        FileS3 file = s3Management.downloadFile(FileS3.builder().path("/" + Buckets.PRINCIPAL_BUCKET.getBucketName() + "/"
                        + dataTransformation.getBankCode() + "/" + dataTransformation.getProcess())
                .name(dataTransformation.getFileName()).build());

        String s = new String(Base64.getDecoder().decode(file.getFileBas64()), StandardCharsets.UTF_8);
        TransformMT940toXML transMT940 = new TransformMT940toXML();
        transMT940.separadorMT940(s);

        if (file.isFound()) {
            System.out.println("File is found");
            System.out.println(file);
        } else {
            System.out.println("File is not found");
        }
    }

}

