package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.java_adv.services.UserService;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("")
    public void saveUser(@RequestParam String name, @RequestParam MultipartFile avatar, @RequestParam String email) throws IOException, MessagingException {
        userService.saveUser(name, avatar, email);
    }

}
