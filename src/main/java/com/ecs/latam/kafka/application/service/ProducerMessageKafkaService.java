package com.ecs.latam.kafka.application.service;

import com.ecs.latam.commons.schemas.TransformationObject;
import com.ecs.latam.kafka.application.port.in.ProducerMessageKafka;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

@Service
@Log4j2
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProducerMessageKafkaService implements ProducerMessageKafka {

    private final KafkaTemplate<String, TransformationObject> kafkaTemplate;


    @Override
    public boolean SendMessageTransformationKafka(String topic, TransformationObject transformationObject) {

        boolean isSending = true;

        try {

            if (transformationObject == null) {
                return false;
            }

            if (!StringUtils.hasText(topic)) {
                return false;
            }

            kafkaTemplate.send(topic, transformationObject);

        } catch (Exception e) {
            isSending = false;
            log.error("Error " + e);
        }
        return isSending;
    }

}

