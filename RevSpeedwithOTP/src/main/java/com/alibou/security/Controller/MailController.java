package com.alibou.security.Controller;


import com.alibou.security.Service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibou.security.Model.MailStructure;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) throws MessagingException {
        mailService.sendMail(mail, mailStructure);
        return "Successfully sent the mail !!";
    }
}
