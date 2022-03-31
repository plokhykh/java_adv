package ua.com.owu.java_adv.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.dao.CardDAO;
import ua.com.owu.java_adv.models.dto.CardDTO;
import ua.com.owu.java_adv.models.entity.Card;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
    private CardDAO cardDAO;

    @PostMapping("")
    public List<Card> createCardWithUser (@RequestBody Card card){
       cardDAO.save(card);
        return cardDAO.findAll();
    }

    @GetMapping("")
    public ResponseEntity<List<CardDTO>> getAllCardsWithUser (){
        List<Card> cardList = cardDAO.findAll();
        List<CardDTO> cardDTOS= cardList.stream().map(CardDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(cardDTOS, HttpStatus.OK);
    }


}
