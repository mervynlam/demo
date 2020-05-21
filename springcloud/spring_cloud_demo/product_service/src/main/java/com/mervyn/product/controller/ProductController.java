package com.mervyn.product.controller;

import com.mervyn.product.entity.Product;
import com.mervyn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping
    public String save(@RequestBody Product product) {
        productService.save(product);
        return "保存成功";
    }
}
