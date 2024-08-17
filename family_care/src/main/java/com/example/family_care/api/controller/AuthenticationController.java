package com.example.family_care.api.controller;

import com.example.family_care.application.service.implementation.AuthenticationService;
import com.example.family_care.domain.entity.UserProfile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(path = "/login")
    private String login(@RequestBody UserProfile profile) {
        return service.login(profile);
    }

}
