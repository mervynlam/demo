package com.mervyn.product.dao;

import com.mervyn.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
