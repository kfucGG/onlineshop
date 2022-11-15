package com.example.onlineshopping.security;

import com.example.onlineshopping.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private BuyerService buyerService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, BuyerService buyerService) {
        this.passwordEncoder = passwordEncoder;
        this.buyerService = buyerService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(buyerService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/adminpage/**").hasRole("ADMIN")
                .antMatchers("/auth/login", "/auth/registration")
                .permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/auth/login")
                .defaultSuccessUrl("/items", true)
                .loginProcessingUrl("/process_login")
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");

    }


}
