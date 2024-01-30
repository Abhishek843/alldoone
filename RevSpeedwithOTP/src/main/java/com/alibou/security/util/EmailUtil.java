//package com.alibou.security.util;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailUtil {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendOtpEmail(String email, String otp) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        mimeMessageHelper.setTo(email);
//        mimeMessageHelper.setSubject("Verify OTP");
//        mimeMessageHelper.setText("""
//        <div>
//          <a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
//        </div>
//        """.formatted(email, otp), true);
//
//        javaMailSender.send(mimeMessage);
//    }
//}
package com.alibou.security.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");

        // Updated setText method to include OTP in the link and message
        mimeMessageHelper.setText("""
        <div>
          <p>Please verify your account by clicking the link below:</p>
          <a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">Click here to verify</a>
          <p>Your OTP: %s</p>
        </div>
        """.formatted(email, otp, otp), true);

        javaMailSender.send(mimeMessage);
    }
}
