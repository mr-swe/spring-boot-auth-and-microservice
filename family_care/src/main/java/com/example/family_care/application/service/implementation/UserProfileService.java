package com.example.family_care.application.service.implementation;

import com.example.family_care.application.service.constract.IUserProfileService;
import com.example.family_care.domain.entity.UserProfile;
import com.example.family_care.domain.repository.UserProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Service
public class UserProfileService implements IUserProfileService {

    private final UserProfileRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileService(UserProfileRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfile createUser(UserProfile request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return repository.save(request);
    }
}
