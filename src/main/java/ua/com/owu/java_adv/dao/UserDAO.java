package ua.com.owu.java_adv.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.java_adv.models.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository <User, Integer> {

    List<User> findByNameAndAge (String name, int age);

}
