package com.kafeinmevlut.garage.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mevlutbeder
 * @created 02/01/2023 04:14
 */
@Slf4j
public class JacksonUtil {
    public static String parseObjectAsPrettyString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("CUSTOM JACKSON LOG HAS THIS EXCEPTION:  " + e.getMessage());
            return "";
        }
    }
}
