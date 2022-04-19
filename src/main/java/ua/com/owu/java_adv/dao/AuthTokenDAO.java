package ua.com.owu.java_adv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.java_adv.models.entity.AuthToken;

public interface AuthTokenDAO extends JpaRepository<AuthToken, Integer> {
    AuthToken findAuthTokenByToken(String token);
}
