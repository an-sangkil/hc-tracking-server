package com.tracking.server.web.handler;

import com.tracking.server.model.LogModel;
import com.tracking.server.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
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

    private KafkaProducerService kafkaProducerService;
    public static final String TOPIC_NAME = "advertising";

    public KafkaHandler(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Bean
    public RouterFunction<ServerResponse> kafkaRouterFunction(KafkaHandler kafkaHandler) {
        return RouterFunctions.route(RequestPredicates.path("/kafka/sender1"), kafkaHandler::producer)
                ;
    }

    private Mono<ServerResponse> producer(ServerRequest serverRequest) {

        String param1 = serverRequest.queryParam("param1").orElseGet(() -> "");
        String data = new LogModel("param1", param1, LocalDateTime.now()).toJson();
        kafkaProducerService.sender(TOPIC_NAME, data);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{code:SUCCESS}")
                .onErrorResume(e -> Mono.just("{ status : error }").flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).syncBody(s)));
    }


}


