package com.wxx.webflux.controller;

import com.wxx.webflux.entity.User;
import com.wxx.webflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/query/user", consumes = "application/json")
    public Flux<User> queryUsers(@RequestBody User user) {
        log.info("-------queryUsers--------");
        Flux<User> userList = userService.findUserList();
        Flux<User> userFlux = userList.flatMap(userElement -> {
            log.info("queryUsers : " + userElement.toString());
            return Mono.just(userElement);
        });
        log.info("-------addUser--------");
        return userFlux;
    }
}
