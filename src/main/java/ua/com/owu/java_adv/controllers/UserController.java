package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.models.dto.UserDetailsDTO;
import ua.com.owu.java_adv.models.entity.User;
import ua.com.owu.java_adv.services.UserService;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/hello")
    public String greeting() {
        return "Hello";
    }

    @PostMapping("/registration")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping("/login")
    public String login() {
        return "Logination success";
    }

    @GetMapping("/info/{id}")
    public UserDetailsDTO userInfo(@PathVariable int id) {
        return userService.findById(id);
    }

}
