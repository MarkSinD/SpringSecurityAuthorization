package com.sinakaev.SpringSecurityTest.security;

import com.sinakaev.SpringSecurityTest.model.User;

import java.util.Optional;

/**
 * DECRIPTION
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
public interface UserService {
    void save(User user);
    Optional<User> findByEmail(String email);
}
