package com.example.family_care.application.service.implementation;

import com.example.family_care.application.service.constract.IUserProfileService;
import com.example.family_care.domain.entity.UserProfile;
import com.example.family_care.domain.repository.UserProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Service
public class UserProfileService implements IUserProfileService {

    private final UserProfileRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserProfileService(UserProfileRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserProfile createUser(UserProfile request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return repository.save(request);
    }
}
