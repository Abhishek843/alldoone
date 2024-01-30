package com.alibou.security.Service;


import com.alibou.security.Model.MailStructure;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

//    @Value("${spring.mail.username}")
//    private String fromMail;

    public void sendMail(String mail, MailStructure mailStructure) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage());
        messageHelper.setFrom("achoure184@gmail.com");
        messageHelper.setTo(mail);
        messageHelper.setSubject(mailStructure.getSubject());
        messageHelper.setText(mailStructure.getMessage(), true);

        mailSender.send(messageHelper.getMimeMessage());


    }
}