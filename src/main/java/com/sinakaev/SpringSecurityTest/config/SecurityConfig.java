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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Permission.DEVELOPERS_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder().username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build()
                ,
                User.builder().username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
                        .build()
                ,
                User.builder().username("alex")
                        .password(passwordEncoder().encode("alex"))
                        .authorities(Role.ADMIN.getAuthorities()).build()

        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
