package ua.com.owu.java_adv.models.dto;

import lombok.Data;
import ua.com.owu.java_adv.models.entity.User;

@Data
public class UserWithPassportDTO {
    private String name;
    private int age;
    private PassportDTO passport;

    public UserWithPassportDTO(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        if(user.getPassport() != null){
            this.passport = new PassportDTO(user.getPassport());
        }
    }
}
