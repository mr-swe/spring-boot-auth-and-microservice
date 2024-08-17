package com.example.family_transaction.application.service.implementation;

import com.example.family_transaction.application.domain.entity.Product;
import com.example.family_transaction.application.domain.repository.ProductRepository;
import com.example.family_transaction.application.service.constract.IProduct;
import org.springframework.stereotype.Service;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@Service
public class ProductService implements IProduct {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product request) {
        return repository.save(request);
    }
}
