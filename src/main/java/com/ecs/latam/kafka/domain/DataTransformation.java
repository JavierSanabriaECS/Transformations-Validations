package com.ecs.latam.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get set
@AllArgsConstructor //constructor con all arg
@NoArgsConstructor //constructor sin arg
@Builder //crear objetos
public class DataTransformation {

    private String bankCode;
    private String fileName;
    private String process;

}
