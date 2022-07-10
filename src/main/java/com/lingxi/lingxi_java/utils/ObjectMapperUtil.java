package com.lingxi.lingxi_java.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper OBJECT_MAPPER_GLOBAL = new ObjectMapper();

    public static ObjectMapper getDefault() {
        return OBJECT_MAPPER_GLOBAL;
    }

    public static ObjectMapper getNewInstance() {
        return new ObjectMapper();
    }

}
