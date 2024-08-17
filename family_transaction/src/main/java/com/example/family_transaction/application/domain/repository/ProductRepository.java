package com.example.family_transaction.application.domain.repository;

import com.example.family_transaction.application.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
