package com.ecs.latam.kafka.domain;

import java.util.List;

public class InvalidMCException extends  Exception{
    public InvalidMCException(List<String> errorList){
        super(errorList.toString());
    }

    public InvalidMCException(Exception e){
        super(e);
    }
}


