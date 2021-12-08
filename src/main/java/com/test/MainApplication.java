package com.test;

import com.test.exceptions.NotFoundException;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
//        UserService userService = (UserService)context.getBean("userServiceImpl");

//        userService.deleteById();
//        userService.save(new User("l","l@mail.ru","24"));
//        userService.updateName();
//
//        try {
//            System.out.println(userService.getById(2);
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(userService.getByEmail("O"));
//
//        for (User x : userService.getAll()){
//            System.out.println(x);
//        }
//        System.out.println(userService.getAllByName("ka"));

    }
}
