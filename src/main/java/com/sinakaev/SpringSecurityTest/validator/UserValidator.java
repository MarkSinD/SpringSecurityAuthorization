package com.sinakaev.SpringSecurityTest.validator;

import com.sinakaev.SpringSecurityTest.model.User;
import com.sinakaev.SpringSecurityTest.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link User} class,
 * implements {@link Validator} interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getFirstName().length() < 3 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName","Size.userForm.firstname");
        }
        if (user.getLastName().length() < 3 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.userForm.lastname");
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
