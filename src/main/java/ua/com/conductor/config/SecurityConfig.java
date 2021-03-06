package ua.com.conductor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/shopping-carts/sessions").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT,"/sessions/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/sessions/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/locations/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/events/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/sessions/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/by-email/").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .and().httpBasic()
                .and()
                .csrf().disable();
    }
}
