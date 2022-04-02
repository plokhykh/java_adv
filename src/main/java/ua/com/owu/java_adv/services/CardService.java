package ua.com.owu.java_adv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.owu.java_adv.dao.CardDAO;
import ua.com.owu.java_adv.models.dto.card.CardDTO;
import ua.com.owu.java_adv.models.dto.card.CardWithUserDTO;
import ua.com.owu.java_adv.models.entity.Card;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardService {
    CardDAO cardDAO;

    public List<CardWithUserDTO> createCardWithUser(Card card) {
        cardDAO.save(card);
        return cardDAO.findAll().stream().map(CardWithUserDTO::new).collect(Collectors.toList());
    }

    public List<CardWithUserDTO> getAllCardsWithUser() {
        List<Card> cardList = cardDAO.findAll();
        return cardList.stream().map(CardWithUserDTO::new).collect(Collectors.toList());
    }
}
