package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.PassportDAO;
import ua.com.owu.java_adv.models.dto.PassportDTO;
import ua.com.owu.java_adv.models.dto.UserDTO;
import ua.com.owu.java_adv.models.entity.Passport;
import ua.com.owu.java_adv.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passports")
@AllArgsConstructor
public class PassportController {
    private PassportDAO passportDAO;

    @GetMapping("")
    public ResponseEntity<List<PassportDTO>> getAllPassports() {
        List<Passport> allPassports = passportDAO.findAll();
        List<PassportDTO> passportDTOS = allPassports.stream().map(PassportDTO::new).collect(Collectors.toList());
        return passportDTOS.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(passportDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable int id, @RequestBody Passport passport) {
        if (passportDAO.findById(id).isPresent()) {
            passport.setId(id);
            PassportDTO passportDTO = new PassportDTO(passportDAO.save(passport));
            return new ResponseEntity<>(passportDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<List<PassportDTO>> saveUser(@RequestBody Passport passport) {
        passportDAO.save(passport);
        List<PassportDTO> passportDTOS = passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(passportDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PassportDTO>> deleteUser(@PathVariable int id) {
        if (passportDAO.findById(id).isPresent()) {
            passportDAO.deleteById(id);
            List<PassportDTO> passportDTOS = passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(passportDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
