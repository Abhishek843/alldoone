package com.alibou.security.help;


import com.alibou.security.help.Email;
import com.alibou.security.help.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailSendController {

//    @Autowired
//    private EmailServices emailService;
//
//    @PostMapping(value = "/sendemail")
////    @Timed(value="sendEmail.time",description="email sending")
//    public ResponseEntity<?> sendEmail(@RequestBody Email email){
//        System.out.println(email);
//        boolean result =this.emailService.sendEmail(email.getSubject(), email.getMessage(),email.getToMail());
//        if(result) {
////            registry.counter("sendEmail.counter").increment();
//            return ResponseEntity.ok("Email is sent successfully.");
//
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not send");
//        }
//    }
}