package com.wxx.webflux.route;

import com.wxx.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRoutes {

    @Bean
    public RouterFunction<ServerResponse> userRoutersFunction(UserHandler userHandler) {
        RouterFunction<ServerResponse> serverResponse = route()
                .GET("/user/{id}", accept(APPLICATION_JSON), userHandler::getUser)
                .GET("/user", accept(APPLICATION_JSON), userHandler::getUserList)
                .POST("/user", userHandler::addUser)
                .build();
        return serverResponse;
    }

}







