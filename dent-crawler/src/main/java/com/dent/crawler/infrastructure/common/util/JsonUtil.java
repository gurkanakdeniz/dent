package com.dent.crawler.infrastructure.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .setSerializationInclusion(Include.NON_NULL).configure(JsonParser.Feature.ALLOW_COMMENTS, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    private static final ObjectWriter writer = mapper.writer();
    private static final ObjectWriter writerPretty = mapper.writerWithDefaultPrettyPrinter();

    public static String toJsonPretty(Object value) {
        try {
            return writerPretty.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("--- to json pretty ---", e);
        }

        return "";
    }

    public static String toJson(Object value) {
        try {
            return writer.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("--- to json ---", e);
        }

        return "";
    }

    public static byte[] toBytes(Object value) {
        try {
            return writer.writeValueAsString(value).getBytes();
        } catch (Exception e) {
            logger.error("--- to bytes ---", e);
        }

        return null;
    }

    public static <T> T toObject(byte[] value, Class<T> cl) {
        try {
            return mapper.readValue(value, cl);
        } catch (Exception e) {
            logger.error("--- to object ---", e);
        }

        return null;
    }

    public static <T> T toObject(String value, Class<T> cl) {
        try {
            return mapper.readValue(value, cl);
        } catch (Exception e) {
            logger.error("--- to object ---", e);
        }

        return null;
    }

    public static Object toObject(String value, Object obj) {
        try {
            return mapper.readValue(value, obj.getClass());
        } catch (Exception e) {
            logger.error("--- to object ---", e);
        }

        return null;
    }

}
