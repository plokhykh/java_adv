package ua.com.owu.java_adv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.java_adv.controllers.MailSenderService;
import ua.com.owu.java_adv.dao.UserDAO;
import ua.com.owu.java_adv.models.entity.User;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private MailSenderService mailSenderService;

    public void saveUser(String name, MultipartFile avatar) throws IOException {
        userDAO.save(new User(name, avatar.getOriginalFilename()));
        String path = System.getProperty("user.home") + File.separator + "avatars" + File.separator;
        avatar.transferTo(new File(path + avatar.getOriginalFilename()));
    }

    public void saveUser(String name, MultipartFile avatar, String email) throws IOException, MessagingException {
        User user = new User(name, avatar.getOriginalFilename(), email);
        String path = System.getProperty("user.home") + File.separator + "avatars" + File.separator;
        mailSenderService.send(user, avatar);
        userDAO.save(user);
        avatar.transferTo(new File(path + avatar.getOriginalFilename()));
    }
}
