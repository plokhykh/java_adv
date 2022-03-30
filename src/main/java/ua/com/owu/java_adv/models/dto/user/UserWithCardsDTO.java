package ua.com.owu.java_adv.models.dto.user;

import lombok.Data;
import ua.com.owu.java_adv.models.dto.CardDTO;
import ua.com.owu.java_adv.models.entity.Card;
import ua.com.owu.java_adv.models.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class UserWithCardsDTO {
    private String name;
    private int age;
    private List<CardDTO> cards;

    public UserWithCardsDTO(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        if (user.getCards() != null) {
            this.cards = new ArrayList<>();

            Iterator<Card> iterator = user.getCards().iterator();
            while (iterator.hasNext()) {
                Card card = iterator.next();
                this.cards.add(new CardDTO(card));
            }
        }
    }
}
