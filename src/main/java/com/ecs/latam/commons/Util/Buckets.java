package com.ecs.latam.commons.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Buckets {

    PRINCIPAL_BUCKET("co.com.ecs.bht");
    private final String bucketName;

}
