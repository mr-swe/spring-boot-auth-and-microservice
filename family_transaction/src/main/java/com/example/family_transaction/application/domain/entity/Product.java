package com.example.family_transaction.application.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@Data
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private String name;
    private String code;
    private String type;
}
