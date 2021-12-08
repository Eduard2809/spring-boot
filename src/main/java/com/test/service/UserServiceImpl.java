package com.test.service;

import com.test.exceptions.BadRequestException;
import com.test.exceptions.NotFoundException;
import com.test.model.Address;
import com.test.model.Status;
import com.test.model.User;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MailSender mailSender;


    @Override
    @Transactional
    @Async
    public void save(User user) throws NotFoundException {
        user.setStatus(Status.UNVERIFIED);
        Address address = null;
        try {
            address = addressService.getById(user.getAddress().getId());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (address != null) {
            user.setAddress(address);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        addressService.save(user.getAddress());
        userRepository.save(user);
    }

    @Override
    public void login(User user) throws BadRequestException, NotFoundException {

        String email = user.getEmail();
        String password = user.getPassword();
        user = userRepository.getByEmailAndPassword(email, password);
        if (user == null) {
            throw new NotFoundException("USER NOT FOUND");
        } else if (user.getStatus().equals(Status.UNVERIFIED)) {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateName(int id, String name) {
        userRepository.setUserInfoById(id, name);
    }

    @Override
    public User getById(int id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("NOT FOUND");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void verified(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null){
            throw new NotFoundException("Not found");
        }
        user.setStatus(Status.VERIFIED);
        userRepository.save(user);
    }

    @Override
    @Async
    public void forgetPassword(String email) throws NotFoundException, MessagingException {
        User user = userRepository.getByEmail(email);
        if (user == null){
            throw new NotFoundException("Not found");
        }
        String code = new GenerateString().generateString();
        mailSender.sendCode(email,"CODE",code);
        user.setResetPasswordToken(code);
        long milliseconds = System.currentTimeMillis();
        user.setResetPasswordTokenCreationDate(milliseconds);
        userRepository.save(user);
    }


    @Override
    public void resetPassword(String email, String code, String password1, String password2) throws NotFoundException, BadRequestException {
        User user = userRepository.getByEmail(email);
        if (user == null){
            throw new NotFoundException("Not found");
        }
        if (userRepository.getByResetPasswordToken(code) != null && password1.equals(password2)){
            if (System.currentTimeMillis() - user.getResetPasswordTokenCreationDate() < 60*1000) {

                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }else throw new BadRequestException();
        }
    }

    @Override
    @Async
    public void sandEmail(String toEmail) throws NotFoundException, MessagingException {
        User user = userRepository.getByEmail(toEmail);
        if (user == null){
            throw new NotFoundException("Not found");
        }
        mailSender.sendSimpleMessage(toEmail,"Verification","" +
                "http://localhost:8080/user/verification?email="+toEmail);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAllByName(String name) {
        return userRepository.getAllByName(name);
    }
}
