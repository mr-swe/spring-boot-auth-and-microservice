package com.example.family_care.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@Controller
public class AuthenticationController {
    @GetMapping(path = "/login")
    private String login() {
        return "login";
    }

}
