package ua.com.owu.java_adv.models.dto.card;

import lombok.Data;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.entity.Card;
import ua.com.owu.java_adv.models.entity.User;

@Data
public class CardDTO {
    private String number;

    public CardDTO(Card card) {
        this.number = card.getNumber();
    }

}
