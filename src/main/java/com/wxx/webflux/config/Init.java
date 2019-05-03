package com.wxx.webflux.config;

import com.wxx.webflux.entity.User;
import com.wxx.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
 
  @Autowired
  UserService userService;
 
  @Override
  public void run(String... args) throws Exception {
 
    userService.setUser("1",new User("Evan",18));
    userService.setUser("2",new User("Weison",19));
    userService.setUser("3",new User("Lucas",17));
    userService.setUser("4",new User("Leo",16));
    userService.setUser("5",new User("Andy",15));
 
  }
}
 

