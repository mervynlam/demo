package com.mervyn.order.controller;

import com.mervyn.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/buy/{id}")
    public Product buy(@PathVariable Long id) {
        Product product = null;
        product = restTemplate.getForObject("http://localhost:9001/product/"+id,Product.class);
        return product;
    }
}
