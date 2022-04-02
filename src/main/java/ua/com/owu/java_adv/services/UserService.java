package ua.com.owu.java_adv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.owu.java_adv.dao.UserDAO;
import ua.com.owu.java_adv.models.dto.user.UserDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithCardsDTO;
import ua.com.owu.java_adv.models.dto.user.UserWithPassportDTO;
import ua.com.owu.java_adv.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;

    public List<UserDTO> findAll() {
        List<User> users = userDAO.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserWithPassportDTO findById(int id) {
        User user = userDAO.findById(id).orElse(new User());
        return new UserWithPassportDTO(user);
    }

    public List<UserWithPassportDTO> findAllWithPassport() {
        List<User> allUsers = userDAO.findAll();
        return allUsers.stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
    }

    public List<UserWithCardsDTO> findAllWithCards() {
        List<User> allUsers = userDAO.findAll();
        return allUsers.stream().map(UserWithCardsDTO::new).collect(Collectors.toList());
    }

    public UserDTO updateUser(int id, User user) {
        user.setId(id);
        return new UserDTO(userDAO.save(user));
    }

    public UserWithPassportDTO updateUserWithPassport(int id,  User user){
        user.setId(id);
        return new UserWithPassportDTO(userDAO.save(user));
    }

    public List<UserDTO> saveUser (User user){
        userDAO.save(user);
        return userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public List<UserWithPassportDTO> saveUserWithPassport (User user){
        userDAO.save(user);
       return userDAO.findAll().stream().map(UserWithPassportDTO::new).collect(Collectors.toList());
    }

    public List<UserWithCardsDTO> saveUserWithCards (User user){
        userDAO.save(user);
        return userDAO.findAll().stream().map(UserWithCardsDTO::new).collect(Collectors.toList());
    }

    public List<UserDTO> deleteUser (int id){
        userDAO.deleteById(id);
        return userDAO.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
