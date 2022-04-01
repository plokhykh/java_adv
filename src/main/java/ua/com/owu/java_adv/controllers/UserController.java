package ua.com.owu.java_adv.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.UserDAO;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithCardsDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithPassportDTO;
import ua.com.owu.java_adv.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserDAO userDAO;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> allUsers = userDAO.findAll();
        List<UserDTO> userDTOS = allUsers.stream().map(UserDTO::new).collect(Collectors.toList());
        return userDTOS.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWithPassportDTO> findById(@PathVariable int id) {
        User user = userDAO.findById(id).orElse(new User());
        UserWithPassportDTO userWithPassportDTO = new UserWithPassportDTO(user);
        return user.getName().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(userWithPassportDTO, HttpStatus.OK);
    }

    @GetMapping("/with-passport")
    public ResponseEntity<List<UserWithPassportDTO>> findAllWithPassport() {
        List<User> allUsers = userDAO.findAll();
        List<UserWithPassportDTO> userWithPassportDTO = allUsers.stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userWithPassportDTO, HttpStatus.OK);
    }

    @GetMapping("/with-cards")
    public ResponseEntity<List<UserWithCardsDTO>> findAllWithCards() {
        List<User> allUsers = userDAO.customQueryUsersWithCards();
        List<UserWithCardsDTO> userWithCardsDTOS = allUsers.stream().map(UserWithCardsDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userWithCardsDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user) {
        if (userDAO.findById(id).isPresent()) {
            user.setId(id);
            UserDTO userDTO = new UserDTO(userDAO.save(user));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/with-passport")
    public ResponseEntity<UserWithPassportDTO> updateUserWithPassport(@PathVariable int id, @RequestBody User user) {
        if (userDAO.findById(id).isPresent()) {
            user.setId(id);
            UserWithPassportDTO userWithPassportDTO = new UserWithPassportDTO(userDAO.save(user));
            return new ResponseEntity<>(userWithPassportDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<List<UserDTO>> saveUser(@RequestBody User user) {
        userDAO.save(user);
        List<UserDTO> userDTOS = userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping("/with-passport")
    public ResponseEntity<List<UserWithPassportDTO>> saveUserWithPassport(@RequestBody User user) {
        userDAO.save(user);
        List<UserWithPassportDTO> userWithPassportDTO = userDAO.findAll().stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userWithPassportDTO, HttpStatus.OK);
    }

    @PostMapping("/with-cards")
    public ResponseEntity<List<UserWithCardsDTO>> saveUserWithCards(@RequestBody User user) {
        userDAO.save(user);
        List<UserWithCardsDTO> userWithCardsDTOS = userDAO.findAll().stream().map(UserWithCardsDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userWithCardsDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTO>> deleteUser(@PathVariable int id) {
        if (userDAO.findById(id).isPresent()) {
            userDAO.deleteById(id);
            List<UserDTO> userDTOS = userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(userDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
