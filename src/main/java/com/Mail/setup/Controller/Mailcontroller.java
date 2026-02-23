package com.Mail.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class Mailcontroller {
    @Autowired
    private  EmailService emailService;


    @PostMapping("/verify")
    public ResponseEntity<?> getVerificationCode(@RequestBody EmailReq req) {
        if (req.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("email is empty");
        }
        String trimEmail = req.getEmail().trim();
        String code = "123456";
        String subject = "Email Verification Code";
        emailService.sendEmail(trimEmail,subject,code);
        return ResponseEntity.ok("email sent successfully");
    }
}
