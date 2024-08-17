package com.example.family_transaction.api.controller;

import com.example.family_transaction.application.domain.entity.Product;
import com.example.family_transaction.application.service.implementation.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    private Product createUser(@RequestBody Product product) {
        return service.createProduct(product);
    }

}
