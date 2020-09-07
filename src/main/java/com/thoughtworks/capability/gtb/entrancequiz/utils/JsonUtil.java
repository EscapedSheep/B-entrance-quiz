package com.thoughtworks.capability.gtb.entrancequiz.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.json.JsonParseException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

public class JsonUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();
    }

    public static String loadTestSuiteResource(String filename) {
        try {
            return StreamUtils.copyToString(FileUtils.class.getResourceAsStream(
                    String.format("/fixtures/%s", filename)),
                    Charset.defaultCharset()
            );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> T unmarshal(String value, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(value, typeRef);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
