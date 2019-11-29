package com.tracking.server.web.handler;

import com.tracking.server.data.model.LogModel;
import com.tracking.server.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description : 카프카 핸들러 및 라우터
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-10-07
 */
@Component
@Slf4j
public class KafkaHandler {
    private ApplicationContext applicationContext;

    public static final String TOPIC_NAME_AD = "advertising";
    public static final String TOPIC_NAME_LOG = "log_topic";

    @Autowired
    public KafkaHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public RouterFunction<ServerResponse> kafkaRouterFunction(KafkaHandler kafkaHandler) {
        return RouterFunctions.route(RequestPredicates.path("/kafka/sender1"), kafkaHandler::producer)
                ;
    }

    private Mono<ServerResponse> producer(ServerRequest serverRequest) {

        String param1 = serverRequest.queryParam("param1").orElseGet(() -> "");
        String data = new LogModel("param1", param1, LocalDateTime.now()).toJson();

        KafkaProducerService kafkaProducerService = applicationContext.getBean(KafkaProducerService.class);
        kafkaProducerService.sender(TOPIC_NAME_AD, data);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{code:SUCCESS}")
                .onErrorResume(e -> Mono.just("{ status : error }").flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).syncBody(s)));
    }


}


