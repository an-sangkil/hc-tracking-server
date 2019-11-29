package com.tracking.server.web.handler;

import com.tracking.server.data.model.LogModel;
import com.tracking.server.service.AccessLogProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description : 엑세스 로그 행들러
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
@Component
public class AccessLogHandler {

    private AccessLogProducerService accessLogProducerService;

    @Autowired
    public AccessLogHandler(AccessLogProducerService accessLogProducerService) {
        this.accessLogProducerService = accessLogProducerService;
    }

    public Mono<ServerResponse> accessLog(ServerRequest serverRequest) {

        String param1 = serverRequest.queryParam("param1").orElseGet(() -> "");
        String data = new LogModel("param1", param1, LocalDateTime.now()).toJson();
        accessLogProducerService.sender(data);


        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody("{code:SUCCESS}")
                .onErrorResume(e -> Mono.just("{ status : error }").flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).syncBody(s)));
    }
}
