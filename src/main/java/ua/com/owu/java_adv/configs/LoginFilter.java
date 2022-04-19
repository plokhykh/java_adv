package ua.com.owu.java_adv.configs;

import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.owu.java_adv.models.dto.UserDTO;
import ua.com.owu.java_adv.services.UserService;
import ua.com.owu.java_adv.configs.utils.Helper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    final Helper helper;

    public LoginFilter(String url, AuthenticationManager authenticationManager, Helper helper) {
        setFilterProcessesUrl(url);
        setAuthenticationManager(authenticationManager);
        this.helper = helper;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserDTO dto = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);

        Authentication authentication = getAuthenticationManager()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
                );
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String jwtToken = helper.getToken(authResult.getName(), "okten");
        helper.setUserToken(authResult.getName(), jwtToken);

        response.addHeader("Authorization", "Bearer " + jwtToken);
        chain.doFilter(request, response);
    }
}
