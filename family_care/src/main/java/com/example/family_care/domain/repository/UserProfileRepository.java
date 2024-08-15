package com.example.family_care.domain.repository;

import com.example.family_care.domain.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
