package com.sinakaev.SpringSecurityTest.controller;

import com.sinakaev.SpringSecurityTest.model.User;
import com.sinakaev.SpringSecurityTest.security.SecurityService;
import com.sinakaev.SpringSecurityTest.security.UserService;
import com.sinakaev.SpringSecurityTest.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * DECRIPTION
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    @Qualifier("securityServiceImpl")
    SecurityService securityService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        System.out.println("Press registration");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("newUser") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors())
            return "registration";

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getConfirmPassword());
        return "redirect:/auth/success";
    }
}
