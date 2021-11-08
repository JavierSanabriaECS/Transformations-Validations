package com.ecs.latam.kafka.application.port.in;

import com.ecs.latam.commons.schemas.TransformationObject;

public interface ProducerMessageKafka {

    boolean SendMessageTransformationKafka(String topic, TransformationObject transformationObject);

}
