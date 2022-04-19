package ua.com.owu.java_adv.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.owu.java_adv.dao.AuthTokenDAO;
import ua.com.owu.java_adv.services.UserService;
import ua.com.owu.java_adv.configs.utils.Helper;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DaoAuthenticationProvider daoAuthenticationProvider;
    private Helper helper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/users/hello").permitAll()
                .antMatchers(HttpMethod.POST, "/users/registration", "/users/login").permitAll()
                .antMatchers(HttpMethod.GET, "/users/info/**").hasAnyRole("USER")
                .and()
                .addFilterBefore(new LoginFilter("/users/login", authenticationManager(), helper), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new RequestsProcessingFilter(helper), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
