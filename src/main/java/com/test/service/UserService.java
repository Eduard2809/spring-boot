package com.test.service;

import com.test.exceptions.BadRequestException;
import com.test.exceptions.NotFoundException;
import com.test.model.User;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll(String email) throws BadRequestException;

    void save(User user) throws NotFoundException, MessagingException;

    void deleteById(int id);

    void updateName(int id, String name);

    User getById(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAllByName(String name);

    List<User> getAllByAge();

    void verified(String email) throws NotFoundException;

    void login(User user) throws BadRequestException, NotFoundException;

    void sandEmail(String toEmail) throws NotFoundException, MessagingException;

    void forgetPassword(String email) throws NotFoundException, MessagingException;

    void resetPassword(String email, String code, String password1, String password2) throws NotFoundException, BadRequestException;
}
