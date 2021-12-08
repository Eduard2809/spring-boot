package com.test.service;

import com.test.exceptions.NotFoundException;
import com.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component("mailSenderClient")
public class MailSender {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String from;


    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, NotFoundException {

        User user = userService.getByEmail(to);

        MimeMessage message1 = emailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(message1);
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);

        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "JAVA LESSONS";
        content = content.replace("[[name]]", user.getName());

        content = content.replace("[[URL]]", text);
        message.setText(content, true);

        emailSender.send(message1);
    }

    public void sendCode(String to, String subject, String text) throws MessagingException, NotFoundException {

        User user = userService.getByEmail(to);

        MimeMessage message1 = emailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(message1);
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);

        String content = "Dear [[name]],<br>"
                + "Please copy this code to reset your password, <br>"
                + " <h3>[[CODE]]</h3> <br> "
                + "JAVA LESSONS";
        content = content.replace("[[name]]", user.getName());

        content = content.replace("[[CODE]]", text);
        message.setText(content, true);

        emailSender.send(message1);
    }

}
