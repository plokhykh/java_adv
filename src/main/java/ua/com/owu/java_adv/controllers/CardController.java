package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.CardDAO;
import ua.com.owu.java_adv.models.dto.card.CardWithUserDTO;
import ua.com.owu.java_adv.models.entity.Card;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
    private CardDAO cardDAO;

    @PostMapping("")
    public ResponseEntity<List<CardWithUserDTO>> createCardWithUser (@RequestBody Card card){
        cardDAO.save(card);
        List<CardWithUserDTO> cardWithUserDTOS = cardDAO.findAll().stream().map(CardWithUserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(cardWithUserDTOS, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CardWithUserDTO>> getAllCardsWithUser (){
        List<Card> cardList = cardDAO.findAll();
        List<CardWithUserDTO> cardDTOS = cardList.stream().map(CardWithUserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(cardDTOS, HttpStatus.OK);
    }


}