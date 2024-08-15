package com.example.family_care.application.service.implementation;

import com.example.family_care.application.service.constract.IUserProfileService;
import com.example.family_care.domain.entity.UserProfile;
import com.example.family_care.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Service
public class UserProfileService implements IUserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile createUser(UserProfile request) {
        return repository.save(request);
    }
}
