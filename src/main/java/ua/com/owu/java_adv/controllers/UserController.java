package ua.com.owu.java_adv.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.UserDAO;
import ua.com.owu.java_adv.models.User;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserDAO userDAO;

    @GetMapping("")
    public ResponseEntity<List<User>> findAll() {
        List<User> response = userDAO.findAll();

        return response.isEmpty() ?
                new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<List<User>>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        User user = userDAO.findById(id).orElse(new User());
        return user.getId() == 0 ?
                new ResponseEntity<User>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (userDAO.findById(id).isPresent()) {
            user.setId(id);
            userDAO.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<List<User>> saveUser(@RequestBody User user) {
        userDAO.save(user);
        return new ResponseEntity<List<User>>(userDAO.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteUser (@PathVariable int id){
        if (userDAO.findById(id).isPresent()) {
            userDAO.deleteById(id);
            return new ResponseEntity<List<User>>(userDAO.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }
}
