package com.sinakaev.SpringSecurityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Security configuration
 *
 * @author Mark Sinakaev
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // В данный момент мы работаем
    // с HTTP клиентом, поэтому вначале мы выбираем его
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        /**
         *  Это создание реального userDetailService
         *  return super.userDetailsService();
         */
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        // passwordEncoder().encode("admin")
                        // вставляет BCrypt password в inMemory хранилище UserDetaile
                        //     InMemoryUserDetailsManager
                        // Содержит Map<String, MutableUserDetails> обьектов UserDetail
                        // и ведет поиск по Map по Username пользователя
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        // Аргумент в конструкторе - это сила шифрования
        return new BCryptPasswordEncoder(12);
    }
}
