package com.example.family_authorization.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Mahfuzur Rahman
 * @Date 8/13/2024
 */

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile extends BaseEntity {

    private String name;
    private String userName;
    private String phone;
    private String password;

}
