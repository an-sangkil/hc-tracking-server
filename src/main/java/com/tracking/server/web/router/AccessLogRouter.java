package com.tracking.server.web.router;

import com.tracking.server.web.handler.AccessLogHandler;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <pre>
 * Description : 엑세스 로그 라우터
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
@Component
public class AccessLogRouter {

    @Bean
    public RouterFunction<ServerResponse> accessLogRouterFunction(AccessLogHandler accessLogHandler){
        return RouterFunctions.route(RequestPredicates.path("/access/logging"),accessLogHandler::accessLog);
    }

}
