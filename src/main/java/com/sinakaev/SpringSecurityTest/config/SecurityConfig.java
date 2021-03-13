package com.sinakaev.SpringSecurityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sinakaev.SpringSecurityTest.model.*;
import org.springframework.http.HttpMethod;
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

        // Стандартная конфигурация
        // super.configure(http);

        // Кастомная конфигурация
        // Аутентификация - друг или враг
        // Авторизация - к каким страницам user имеет доступ
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
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
                        // и ведет поиск по Map по Username пользователя\
                        .password(passwordEncoder().encode("admin"))
                        .roles(Role.ADMIN.name())
                        .build(),
                User.builder().username("user")
                .password(passwordEncoder().encode("user"))
                .roles(Role.USER.name())
                .build()
                ,
                User.builder().username("alex")
                .password(passwordEncoder().encode("alex"))
                .roles(Role.ADMIN.name(), Role.USER.name()).build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        // Аргумент в конструкторе - это сила шифрования
        return new BCryptPasswordEncoder(12);
    }
}
