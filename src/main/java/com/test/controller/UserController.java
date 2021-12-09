package com.test.controller;

import com.test.exceptions.BadRequestException;
import com.test.exceptions.NotFoundException;
import com.test.model.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @RolesAllowed(value = "ROLE_ADMIN")
    @RequestMapping("/get")
    @GetMapping
    public List<User> getAll(String email) throws BadRequestException {
        return userService.getAll(email);
    }

    @GetMapping
    @RequestMapping("/age")
    public List<User> getAllByAge(){
        return userService.getAllByAge();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable int id) throws NotFoundException {
       return userService.getById(id);
    }

    @RequestMapping("/get-by-email")
    @GetMapping
    public User getByEmail(@RequestParam("email") String email) throws NotFoundException {
        return userService.getByEmail(email);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) {
        userService.deleteById(id);
    }


    @PutMapping
    public void updateName(@RequestParam("id") int id,@RequestParam String name){
        userService.updateName(id, name);
    }

    @PostMapping
    @RequestMapping("/save")
    public String save(@RequestBody User user) throws NotFoundException, MessagingException {
        userService.save(user);
//        userService.sandEmail(user.getEmail());
        return "If you want to verify your mail please check your massages";
    }
    @PostMapping
    @RequestMapping("/login")
    public void login(@RequestBody User user) throws BadRequestException, NotFoundException {
        userService.login(user);
    }

    @PostMapping
    @RequestMapping("/verification")
    public void verify(@RequestParam String email) throws NotFoundException {
        userService.verified(email);
    }


    @PostMapping
    @RequestMapping("/to-email")
    public void sendToEmail(@RequestParam String email) throws NotFoundException, MessagingException {
        userService.sandEmail(email);
    }
    @PostMapping
    @RequestMapping("/forget-password")
    public void forgetPassword(@RequestParam String email) throws NotFoundException, MessagingException {
        userService.forgetPassword(email);
    }

    @PostMapping
    @RequestMapping("/reset-password")
    public void resetPassword(@RequestParam String email, String code, String pass1, String pass2) throws NotFoundException, BadRequestException {
        userService.resetPassword(email, code, pass1, pass2);
    }


}
