package com.nocountry.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendMail (String destino,String asunto,String texto){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("mimail@gmail.com");
        mensaje.setTo(destino);
        mensaje.setSubject(asunto);
        mensaje.setText(texto);

        mailSender.send(mensaje);
    }

    public void sendMailTemplate(String destino, String asunto){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            Context context = new Context();
            String htmlText = templateEngine.process("email-template",context);
            helper.setFrom("sodaclaro@gmail.com");
            helper.setTo(destino);
            helper.setSubject(asunto);
            helper.setText(htmlText,true);
            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}