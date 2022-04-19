package ua.com.owu.java_adv.configs.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.owu.java_adv.dao.AuthTokenDAO;
import ua.com.owu.java_adv.models.entity.AuthToken;
import ua.com.owu.java_adv.models.entity.User;
import ua.com.owu.java_adv.services.UserService;

import java.util.Collection;


@Data
@AllArgsConstructor
@Configuration
public class Helper {
    private  UserService userService;
    private AuthTokenDAO authTokenDAO;

    public String getToken(String subject, String sign) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, sign.getBytes())
                .compact();
    }

    public void setUserToken (String username, String jwtToken){
        User user = userService.findByName(username);
        AuthToken authToken = new AuthToken();
        authToken.setToken(jwtToken);
        authToken.setUser(user);
        user.getAuthTokens().add(authToken);
        userService.save(user);
    }

    public void checkAuthorization (String authorization){
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");
            AuthToken userToken = authTokenDAO.findAuthTokenByToken(token);
            User user = userToken.getUser();
            if (user != null) {
                String username = user.getUsername();
                String password = user.getPassword();
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                UsernamePasswordAuthenticationToken authenticationObject = new UsernamePasswordAuthenticationToken(username, password, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationObject);
            }
        }
    }
}
