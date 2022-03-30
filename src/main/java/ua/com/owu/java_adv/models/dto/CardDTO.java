package ua.com.owu.java_adv.models.dto;

import lombok.Data;
import ua.com.owu.java_adv.models.entity.Card;

@Data
public class CardDTO {
    private String number;

    public CardDTO(Card card) {
        this.number = card.getNumber();
    }

}
