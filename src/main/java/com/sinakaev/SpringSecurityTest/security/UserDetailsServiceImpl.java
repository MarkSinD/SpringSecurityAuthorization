package com.sinakaev.SpringSecurityTest.security;

import com.sinakaev.SpringSecurityTest.model.User;
import com.sinakaev.SpringSecurityTest.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * При вводе логина и пароля работает следующий алгоритм:
 * 1. Вызывается метод loadUserByUsername
 * 2. Метод метод пытается найти по имени
 * 3. Если поиск по имени успешен, тогда создается и возвращается SpringSecurity User
 *
 * @author Mark Sinakaev
 * @version 1.0
 */

@Service("userDetailsServiceImpl")
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
