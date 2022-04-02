package ua.com.owu.java_adv.models.dto;

import lombok.Data;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.entity.City;
import ua.com.owu.java_adv.models.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CityWithUsersDTO {
    private String cityName;
    private List<UserDTO> users;

    public CityWithUsersDTO(City city){
        this.cityName = city.getCityName();
        if (city.getUsers() != null) {
            this.users = new ArrayList<>();

            Iterator<User> iterator = city.getUsers().iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                this.users.add(new UserDTO(user));
            }
        }

    }
}
