package com.example.family_authorization.infrastructurer.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mahfuzur Rahman
 * @Date 8/21/2024
 */

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String errorMessage = null;
        Exception exception = (Exception) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "No User Found";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Bad Credentials";
        } else {
            errorMessage = "Invalid credentials";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

}
