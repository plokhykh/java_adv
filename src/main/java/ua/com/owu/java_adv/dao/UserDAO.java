package ua.com.owu.java_adv.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.owu.java_adv.models.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository <User, Integer> {

    List<User> findByNameAndAge (String name, int age);

    @Query("select u from User u join fetch u.cities")
    List<User> customQueryUsersWithFetchCities();

}
