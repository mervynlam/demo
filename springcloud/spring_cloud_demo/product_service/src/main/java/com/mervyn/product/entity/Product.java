package com.mervyn.product.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="tb_product")
public class Product {
    @Id
    private Long id;
    private String product_name;
    private Integer status;
    private BigDecimal price;
    private String product_desc;
    private String caption;
    private String inventory;
}
