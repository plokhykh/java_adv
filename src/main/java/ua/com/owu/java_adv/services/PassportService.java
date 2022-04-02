package ua.com.owu.java_adv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.owu.java_adv.dao.PassportDAO;
import ua.com.owu.java_adv.models.dto.PassportDTO;
import ua.com.owu.java_adv.models.entity.Passport;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassportService {
    private PassportDAO passportDAO;

    public List<PassportDTO> getAllPassports() {
        List<Passport> allPassports = passportDAO.findAll();
        return allPassports.stream().map(PassportDTO::new).collect(Collectors.toList());
    }

    public PassportDTO updatePassport(int id, Passport passport) {
        passport.setId(id);
        return new PassportDTO(passportDAO.save(passport));
    }

    public List<PassportDTO> saveUser(Passport passport) {
        passportDAO.save(passport);
        return passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
    }

    public List<PassportDTO> deleteUser(int id) {
        passportDAO.deleteById(id);
        return passportDAO.findAll().stream().map(PassportDTO::new).collect(Collectors.toList());
    }
}
