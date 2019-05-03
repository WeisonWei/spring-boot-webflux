package com.wxx.webflux.handler;

import com.wxx.webflux.entity.User;
import com.wxx.webflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class UserHandler {
    @Autowired
    UserService userService;


    public Mono<ServerResponse> getUserList(ServerRequest request) {

        Flux<User> userFlux = userService.findUserList();
        userFlux.flatMap(user -> {
            log.info(user.toString());
            return Mono.just(user);
        });
        return ServerResponse.ok().body(userFlux, User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {

        String userId = request.pathVariable("userId");
        Mono<User> userMono = userService.findUserById(userId);
        userMono.flatMap(user -> {
            log.info(user.toString());
            return Mono.just(user);
        });
        return ServerResponse.ok().body(userMono, User.class);
    }

    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<User> userMonoResult = userService.addUser(userMono);
        return ServerResponse.ok().body(userMonoResult, User.class);
    }
}
 
