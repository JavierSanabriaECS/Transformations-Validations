package com.ecs.latam.kafka.domain;

import java.util.List;

public class InvalidMTException extends Exception {
    public InvalidMTException(List<String> errorList){
        super(errorList.toString());

    }

}
