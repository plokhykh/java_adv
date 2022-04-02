package ua.com.owu.java_adv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.models.dto.PassportDTO;
import ua.com.owu.java_adv.models.entity.Passport;
import ua.com.owu.java_adv.services.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportController {
    PassportService passportService;

    @GetMapping("")
    public ResponseEntity<List<PassportDTO>> getAllPassports() {
        List<PassportDTO> allPassports = passportService.getAllPassports();
        return allPassports.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(allPassports, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable int id, @RequestBody Passport passport) {
        return new ResponseEntity<>(passportService.updatePassport(id, passport), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<PassportDTO>> saveUser(@RequestBody Passport passport) {
        return new ResponseEntity<>(passportService.saveUser(passport), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PassportDTO>> deleteUser(@PathVariable int id) {
        return new ResponseEntity<>(passportService.deleteUser(id), HttpStatus.OK);
    }
}
