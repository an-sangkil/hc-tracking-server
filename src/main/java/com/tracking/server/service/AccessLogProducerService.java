package com.tracking.server.service;

import com.tracking.server.web.handler.KafkaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description : 엑세스 로그 발행자
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, scopeName = "prototype")
public class AccessLogProducerService {
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public AccessLogProducerService(KafkaTemplate<String,String>  kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sender(String data) {
        kafkaTemplate.send(KafkaHandler.TOPIC_NAME_LOG, data);
    }
}
