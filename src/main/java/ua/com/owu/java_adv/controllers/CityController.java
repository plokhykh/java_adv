package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.CityDAO;
import ua.com.owu.java_adv.models.dto.CityWithUsersDTO;
import ua.com.owu.java_adv.models.entity.City;
import ua.com.owu.java_adv.services.CityService;

import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {
    CityService cityService;

    @PostMapping()
    public ResponseEntity<List<CityWithUsersDTO>> saveCityWithUser(@RequestBody City city) {
        return new ResponseEntity<>(cityService.saveCityWithUser(city), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CityWithUsersDTO>> getCityWithUser() {
        return new ResponseEntity<>(cityService.getCityWithUser(), HttpStatus.OK);
    }
}
