package com.sinakaev.SpringSecurityTest.model;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;

/**
 * Class represent table User
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String confirmPassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
