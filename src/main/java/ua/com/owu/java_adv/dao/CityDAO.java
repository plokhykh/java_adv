package ua.com.owu.java_adv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.owu.java_adv.models.entity.City;

import java.util.List;

public interface CityDAO extends JpaRepository<City, Integer> {

    @Query("select c from City c join fetch c.users")
    List<City> customQueryCitiesFromFetchUsers();
}
