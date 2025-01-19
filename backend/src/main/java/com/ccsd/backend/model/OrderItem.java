package com.ccsd.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String slug;
    private String name;
    private Integer quantity;
    private String image;
    private Double price;

    @DBRef
    private Product product;
}