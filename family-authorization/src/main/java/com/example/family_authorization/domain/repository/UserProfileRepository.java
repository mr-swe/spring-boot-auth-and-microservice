package com.example.family_authorization.domain.repository;

import com.example.family_authorization.domain.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUserNameAndIsDeletedFalse(String userName);

}
