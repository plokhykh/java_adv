package ua.com.owu.java_adv.configs;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ua.com.owu.java_adv.configs.utils.Helper;
import ua.com.owu.java_adv.dao.AuthTokenDAO;
import ua.com.owu.java_adv.models.entity.AuthToken;
import ua.com.owu.java_adv.models.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

public class RequestsProcessingFilter extends GenericFilterBean {

    final Helper helper;

    public RequestsProcessingFilter(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorization = request.getHeader("Authorization");

        helper.checkAuthorization(authorization);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
