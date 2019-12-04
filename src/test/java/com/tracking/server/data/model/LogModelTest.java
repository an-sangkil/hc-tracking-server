package com.tracking.server.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-12-03
 */
@Slf4j
public class LogModelTest {

    @Test
    public void test1() throws IOException {
        LogModel logModel = new LogModel();
        logModel.setTime(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        var stringData = objectMapper.writeValueAsString(logModel);
        log.info("stringData = {}", stringData);

        LogModel logModel1 = objectMapper.readValue(stringData, LogModel.class);
        log.info("stringData to Object = {}", logModel1);
    }





}