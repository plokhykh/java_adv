package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.models.dto.card.CardWithUserDTO;
import ua.com.owu.java_adv.models.entity.Card;
import ua.com.owu.java_adv.services.CardService;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
    CardService cardService;

    @PostMapping("")
    public ResponseEntity<List<CardWithUserDTO>> createCardWithUser(@RequestBody Card card) {
        return new ResponseEntity<>(cardService.createCardWithUser(card), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CardWithUserDTO>> getAllCardsWithUser() {
        return new ResponseEntity<>(cardService.getAllCardsWithUser(), HttpStatus.OK);
    }

}