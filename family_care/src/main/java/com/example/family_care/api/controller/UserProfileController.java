package com.example.family_care.api.controller;

import com.example.family_care.application.service.implementation.UserProfileService;
import com.example.family_care.domain.entity.UserProfile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@RestController
@RequestMapping(path = "/user-profile")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    private UserProfile createUser(@RequestBody UserProfile profile) {
        return service.createUser(profile);
    }

}
