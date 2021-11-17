package com.ecs.latam.kafka.application.service;

import com.ecs.latam.bhfilemanager.BHFile;
import com.ecs.latam.bhfilemanager.BHFileDownloadRequest;
import com.ecs.latam.bhfilemanager.FileManagerPort;
import com.ecs.latam.bhsharedkernel.schemas.RouterObject;
import com.ecs.latam.kafka.domain.DataTransformation;
import com.ecs.latam.kafka.domain.TransformMT940toXML;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ConsumerMessageKafkaService {

  private final FileManagerPort fileManagerPort;

  @KafkaListener(topics = "Transformation1", groupId = "FIRST_GROUP")
  public void transformation(ConsumerRecord<String, RouterObject> routerConsumerRecord)
      throws DatatypeConfigurationException, ParseException {
    System.out.println("<<<<START TRANSFORMATION>>>>");
    System.out.println(routerConsumerRecord.value());


    Optional<BHFile> bhFile =
        fileManagerPort.downloadFile(
            this.mapDataTransformationToBHFileDownloadRequest(routerConsumerRecord.value()));

    if (bhFile.isPresent()) {
      try {
        String fileContent = decodeFile(bhFile);
        TransformMT940toXML transMT940 = new TransformMT940toXML();
        transMT940.separadorMT940(fileContent);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("File is not found");
    }
  }

  private String decodeFile(Optional<BHFile> bhFile) throws IOException {
    return new String(
        Base64.getDecoder().decode(bhFile.get().getFileStream().readAllBytes()),
        StandardCharsets.UTF_8);
  }

  private BHFileDownloadRequest mapDataTransformationToBHFileDownloadRequest(
          RouterObject dataTransformation) {

    return BHFileDownloadRequest.builder()
        .bucket(dataTransformation.getIntegrationCode().toString())
        .name(dataTransformation.getFileName().toString())
        .build();
  }
}
