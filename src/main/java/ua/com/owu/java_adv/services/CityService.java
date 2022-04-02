package ua.com.owu.java_adv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.owu.java_adv.dao.CityDAO;
import ua.com.owu.java_adv.models.dto.CityWithUsersDTO;
import ua.com.owu.java_adv.models.entity.City;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {
    CityDAO cityDAO;

    public List<CityWithUsersDTO> saveCityWithUser (City city){
        cityDAO.save(city);
        return cityDAO.customQueryCitiesFromFetchUsers().stream().map(CityWithUsersDTO::new).collect(Collectors.toList());
    }

    public List<CityWithUsersDTO> getCityWithUser (){
        return cityDAO.customQueryCitiesFromFetchUsers().stream().map(CityWithUsersDTO::new).collect(Collectors.toList());
    }
}
