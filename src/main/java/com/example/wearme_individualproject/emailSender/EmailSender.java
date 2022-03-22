package com.example.wearme_individualproject.emailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;

@SpringBootApplication
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationRegistrationEmail(String emailReceiver) throws AddressException, MessagingException, IOException {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailReceiver);

        msg.setSubject("Successful registration");
        msg.setText("Your registration at Wear Me was successful!");

        javaMailSender.send(msg);

    }
    public void sendOrderConfirmationEmail(String emailReceiver, String username, String billingAddress) throws AddressException, MessagingException, IOException {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailReceiver);

        msg.setSubject(username+", your order at WEAR ME");
        msg.setText("Your order was received successfully! \r\n Your billing address is: \r\n" + billingAddress) ;

        javaMailSender.send(msg);

    }
}
