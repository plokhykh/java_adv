package ua.com.owu.java_adv.models.dto.user;

import lombok.Data;
import ua.com.owu.java_adv.models.entity.User;

@Data
public class UserDTO {
    private String name;
    private int age;


    public UserDTO (User user){
        this.name = user.getName();
        this.age = user.getAge();
    }
}
