package com.ecs.latam.kafka.adapter.in.web;

import com.ecs.latam.commons.schemas.TransformationObject;
import com.ecs.latam.kafka.application.port.in.ProducerMessageKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("produce")
public class ProducerMessageController {

    private final ProducerMessageKafka producerMessageKafka;

    @PostMapping
    public boolean produceMessage() {
        return producerMessageKafka.SendMessageTransformationKafka("Transformation1", TransformationObject.newBuilder().setBankCode("BCO").setProcess("STATEMENT").setFileName("MT940_Pages.txt").build());
    }

}

