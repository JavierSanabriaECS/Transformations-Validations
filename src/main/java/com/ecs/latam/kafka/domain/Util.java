package com.ecs.latam.kafka.domain;

public class Util {

    static boolean isEmptyTag(String tag){
        boolean isEmpty =false;
        if(tag.trim().equals("") || tag.isEmpty()){
            isEmpty = true;
        }
        return isEmpty;
    }


}
