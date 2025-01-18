package com.ccsd.backend.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String slug;
    private String category;
    private String image;
    private Double price;
    private Integer countInStock;
    private String brand;
    private Double rating;
    private Integer numReviews;
    private String description;
}

