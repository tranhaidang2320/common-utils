package com.dangiscoding.commonutils.json;

import com.dangiscoding.commonutils.json.exception.ObjectFromJsonException;
import com.dangiscoding.commonutils.json.exception.ObjectToJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class JsonUtils {
    private static final JsonMapper jsonMapper;

    static {
        jsonMapper = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .build();
    }

    private JsonUtils() {
    }

    public static String toJson(Object object) {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (Exception e) {
            String errorMsg = "JsonUtils.toJson Exception: " + e.getMessage();
            throw new ObjectToJsonException(errorMsg, e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return jsonMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            String errorMsg = "JsonUtils.fromJson Exception: " + e.getMessage();
            throw new ObjectFromJsonException(errorMsg, e);
        }
    }
}
