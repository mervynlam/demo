package com.mervyn.product.service;

import com.mervyn.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    List findAll();

    void save(Product product);

    void update(Product product);

    void delete(Long id);
}
