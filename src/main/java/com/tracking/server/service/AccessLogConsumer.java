package com.tracking.server.service;

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

import com.tracking.server.config.LogMaker;
import com.tracking.server.web.handler.KafkaHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description : 엑세스 로그 구독자
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
public class AccessLogConsumer {

    private AccessLogService accessLogService;
    /**
     * 카프카 파티션이 0,1 번인 것만 리슨, 시작시 바로 실행
     * @param consumerRecord
     */
    @KafkaListener(
//            groupId = "log-group",
            id = "id-log-1",
            topicPartitions = {
                    @TopicPartition(topic = KafkaHandler.TOPIC_NAME_LOG, partitions = {"0", "1"})
            },
            autoStartup = "true"
    )
    public void receiveTopic1(ConsumerRecord consumerRecord) throws Exception {

        String logData = (String) consumerRecord.value();
        log.info("receive on topic - log 1 = {}", consumerRecord.toString());

        accessLogService.saveAccessLog(logData);

    }
}
