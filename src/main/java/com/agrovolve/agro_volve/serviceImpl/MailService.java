package com.agrovolve.agro_volve.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service

public class MailService {
 @Value("${spring.mail.password}")
private String from;

@Value("${spring.mail.username}")
private String  fom;


    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            System.out.println(from.toString());
            System.out.println(fom.toString());
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); 
            helper.setFrom(fom);

            mailSender.send(message);
            System.out.println("Mail sent successfully!");

        //     SimpleMailMessage simple = new SimpleMailMessage();

        //    simple.setTo(to);
        //     simple.setSubject(subject);
        //     simple.setText(body); 
        //     simple.setFrom(fom);

        //     mailSender.send(simple);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
