package com.wxx.webflux.service;

import com.wxx.webflux.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private Map<String, User> userMap = new ConcurrentHashMap<>();


    public Mono<User> addUser(Mono<User> user) {
        Set<String> strings = userMap.keySet();
        Optional<String> max = strings.stream().max(String::compareTo);
        Mono<User> userMono = user.flatMap(userElement -> {
            userMap.put(String.valueOf(Integer.valueOf(max.get() + 1)), userElement);
            return Mono.just(userElement);
        });
        return userMono;
    }

    public Mono<User> findUserById(String userId) {
        User user = userMap.getOrDefault(userId, new User("nick", 18));
        return Mono.just(user);
    }

    public Flux<User> findUserList() {
        List<User> userList = new ArrayList<>();
        Set<Map.Entry<String, User>> entries = userMap.entrySet();
        entries.stream().forEach(entry -> userList.add(entry.getValue()));
        return Flux.fromStream(userList.stream());
    }

    public void setUser(String userId, User user) {
        userMap.put(userId, user);
    }

}

