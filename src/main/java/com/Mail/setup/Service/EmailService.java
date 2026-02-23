package com.Mail.setup.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private  final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    "UTF-8"
            );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("chibuzoranthonyajibo@gmail.com");

            String htmlMsg = """
            <div style="font-family: Arial, sans-serif; line-height: 1.6;">
                <h3>Welcome to SkillLink</h3>

                <p>
                    This email was provided for an account registration on SkillLink.
                    If this action was not performed by you, please ignore this email.
                </p>

                <p>Use the below code to verify your email:</p>

                <h2 style="
                    color: purple;
                    background: #f3f3f3;
                    display: inline-block;
                    padding: 12px 20px;
                    border-radius: 6px;
                    letter-spacing: 2px;
                ">
                    %s
                </h2>

                <p>Thank you for joining us!</p>
            </div>
            """.formatted(code);

            helper.setText(htmlMsg, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

}
