package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.java_adv.models.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailSenderService {
    private JavaMailSender javaMailSender;

    public void send(User user, MultipartFile multipartFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
            mimeMessage.setFrom(new InternetAddress(""));
            helper.setTo(user.getEmail());
            helper.setText("<h1>Hello "+user.getName()+"</h1>", true);

            String fileFormat = multipartFile.getContentType().split("/")[1];
            helper.addAttachment("somefilename." + fileFormat, multipartFile);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }
}

