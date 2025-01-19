package com.ccsd.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private String slug;
    private String name;
    private Integer quantity;
    private String image;
    private Double price;
    private String productId;
}
