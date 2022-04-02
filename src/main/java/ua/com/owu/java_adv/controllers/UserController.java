package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithCardsDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithPassportDTO;
import ua.com.owu.java_adv.models.entity.User;
import ua.com.owu.java_adv.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWithPassportDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/with-passport")
    public ResponseEntity<List<UserWithPassportDTO>> findAllWithPassport() {
        return new ResponseEntity<>(userService.findAllWithPassport(), HttpStatus.OK);
    }

    @GetMapping("/with-cards")
    public ResponseEntity<List<UserWithCardsDTO>> findAllWithCards() {
        return new ResponseEntity<>(userService.findAllWithCards(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PatchMapping("/{id}/with-passport")
    public ResponseEntity<UserWithPassportDTO> updateUserWithPassport(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserWithPassport(id, user), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<UserDTO>> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/with-passport")
    public ResponseEntity<List<UserWithPassportDTO>> saveUserWithPassport(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUserWithPassport(user), HttpStatus.OK);
    }

    @PostMapping("/with-cards")
    public ResponseEntity<List<UserWithCardsDTO>> saveUserWithCards(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUserWithCards(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTO>> deleteUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}
