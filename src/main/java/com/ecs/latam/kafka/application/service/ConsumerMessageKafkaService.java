package com.ecs.latam.kafka.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.ecs.latam.bhfilemanager.BHFile;
import com.ecs.latam.bhfilemanager.BHFileDownloadRequest;
import com.ecs.latam.bhfilemanager.FileManagerPort;
import com.ecs.latam.bhfilemanager.S3FileManager;
import com.ecs.latam.bhsharedkernel.schemas.RouterObject;
import com.ecs.latam.kafka.domain.TransformMT940toXML;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
public class ConsumerMessageKafkaService {

  private final AmazonS3 amazonS3;
  private final FileManagerPort fileManagerPort;

  public ConsumerMessageKafkaService(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
    this.fileManagerPort = new S3FileManager(this.amazonS3);
  }

  @KafkaListener(topics = "Transformation1", groupId = "FIRST_GROUP")
  public void transformation(ConsumerRecord<String, RouterObject> routerConsumerRecord)
      throws DatatypeConfigurationException, ParseException {
    log.debug("<<<<START TRANSFORMATION>>>>");
    RouterObject routerObject = routerConsumerRecord.value();
    log.debug("Route to: %s ", routerObject);


    Optional<BHFile> bhFile =
        fileManagerPort.downloadFile(
            this.mapRouterObjectToBHFileDownloadRequest(routerObject));

    if (bhFile.isPresent()) {
      try {
        String fileContent = decodeFile(bhFile);
        TransformMT940toXML transMT940 = new TransformMT940toXML();
        String separadorMT940 = transMT940.separadorMT940(fileContent);
        log.debug(separadorMT940);
        log.debug("<<<<END TRANSFORMATION>>>>");
      } catch (IOException e) {
        log.error("Error transforming file -> ",e);
      }
    } else {
      log.error("File is not found");
    }
  }

  private String decodeFile(Optional<BHFile> bhFile) throws IOException {
    return new String(
        Base64.getDecoder().decode(bhFile.get().getFileStream().readAllBytes()),
        StandardCharsets.UTF_8);
  }

  private BHFileDownloadRequest mapRouterObjectToBHFileDownloadRequest(
          RouterObject dataTransformation) {

    return BHFileDownloadRequest.builder()
        .bucket(dataTransformation.getIntegrationCode().toString())
        .name(dataTransformation.getFileName().toString())
        .build();
  }
}
