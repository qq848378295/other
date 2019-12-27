package org.zxg.utils.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
           log.error(e.getMessage(),e);
           return null;
        }
    }

    public static <T> T json2obj(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static Map<String, Object> json2map(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        Map<String, Map<String, Object>> map = null;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map>() {});
        } catch (IOException e) {
            return null;
        }
        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2obj(entry.getValue(), clazz));
        }
        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List>() {});
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            result.add(map2obj(map, clazz));
        }
        return result;
    }

    public static <T> T map2obj(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
