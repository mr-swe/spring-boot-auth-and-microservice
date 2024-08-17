package com.example.family_care.application.service.implementation;

import com.example.family_care.application.service.constract.IAuthentication;
import com.example.family_care.domain.entity.UserProfile;
import com.example.family_care.domain.repository.UserProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Service
public class AuthenticationService implements IAuthentication {

    private final UserProfileRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationService(UserProfileRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String login(UserProfile request) {
        Optional<UserProfile> userOptional = repository.findByUserNameAndIsDeletedFalse(request.getUserName());

        if (userOptional.isPresent()) {
            UserProfile user = userOptional.get();
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                return "Success";
            } else {
                return "Password error";
            }
        } else return "User not found";
    }

}
