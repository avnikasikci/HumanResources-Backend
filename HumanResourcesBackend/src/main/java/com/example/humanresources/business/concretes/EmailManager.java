package com.example.humanresources.business.concretes;

import  com.example.humanresources.business.abstracts.EmailService;
import com.example.humanresources.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailManager implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private final JavaMailSender javaMailSender;

//    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    public EmailManager (JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendVerifyEmail(User user, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("HRMS Mail Dogrulama");
        message.setText("Hrms kayıt işleminizi tamamlamak için linke tıklayınız: https://kodlamaio-hrms.herokuapp.com/api/activationcode/active/"+code);
        message.setTo(user.getEmail());
        message.setFrom("bilgi@avnikasikci.com");


        emailSender.send(message);

        //email gönderme kodları
    }
    public void sendSimpleMessage(     String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }
    @Override
    public void sendSimpleMessageV2 (String receiver, String sender, String message, String filenameAndLocation) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare (MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setTo(receiver);
                messageHelper.setFrom(sender);
                messageHelper.setSubject("test subject");
                messageHelper.setText(message);
            }
        };
        this.javaMailSender.send(preparator);
    }

}
