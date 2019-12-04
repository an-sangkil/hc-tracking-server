package com.tracking.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tracking.server.data.cassandra.AccessLogEntity;
import com.tracking.server.data.model.LogModel;
import com.tracking.server.repository.jpa.AccessLogRepository;
import com.tracking.server.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
@Service
@Slf4j
public class AccessLogService {
    private AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    public void saveAccessLog(String data) throws Exception{

        log.debug("data = {}", data);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //objectMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

        LogModel  logModel =objectMapper.readValue(data, LogModel.class);

        AccessLogEntity accessLogEntity = new AccessLogEntity();
        accessLogEntity.setId(UUID.randomUUID().toString());
        accessLogEntity.setClicks(new Random(100).nextLong());
        accessLogEntity.setTime(logModel.getTime());

        NumberUtils numberUtils = new NumberUtils();
        var clicks = new Random().nextInt(10000)+1001;
        var impressions  = new Random().nextInt(10000)+1001;
        var videoViews = new Random().nextInt(10000)+1001;
        var ctr = numberUtils.getRandomDoubleBetweenRange(100000, 900000);
        var vtr = numberUtils.getRandomDoubleBetweenRange(100000, 900000);
        accessLogEntity.setClicks((long) clicks);
        accessLogEntity.setImpressions((long) impressions);
        accessLogEntity.setVideoViews((long) videoViews);
        accessLogEntity.setSpend(900000000L);
        accessLogEntity.setCtr((double) ctr);
        accessLogEntity.setVtr((double) vtr);

        Mono<AccessLogEntity> logModelMono = accessLogRepository.save(accessLogEntity);
        logModelMono.subscribe();
    }
}
