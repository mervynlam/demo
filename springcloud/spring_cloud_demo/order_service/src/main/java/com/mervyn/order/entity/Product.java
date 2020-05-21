package com.mervyn.order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String product_name;
    private Integer status;
    private BigDecimal price;
    private String product_desc;
    private String caption;
    private String inventory;
}
