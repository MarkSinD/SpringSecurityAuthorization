package com.sinakaev.SpringSecurityTest.security;

import com.sinakaev.SpringSecurityTest.dao.UserRepository;
import com.sinakaev.SpringSecurityTest.model.Role;
import com.sinakaev.SpringSecurityTest.model.Status;
import com.sinakaev.SpringSecurityTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DECRIPTION
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Bean("bCryptPasswordEncoder")
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}


