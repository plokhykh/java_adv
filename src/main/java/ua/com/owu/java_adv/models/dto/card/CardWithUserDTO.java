package ua.com.owu.java_adv.models.dto.card;

import lombok.Data;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.entity.Card;

@Data
public class CardWithUserDTO {
    private String number;
    private UserDTO user;


    public CardWithUserDTO(Card card) {
        this.number = card.getNumber();
        this.user = new UserDTO(card.getUser());
    }


}