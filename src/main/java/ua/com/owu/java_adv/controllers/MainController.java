package ua.com.owu.java_adv.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
public class MainController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

}
