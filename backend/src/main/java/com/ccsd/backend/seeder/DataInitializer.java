package com.ccsd.backend.seeder;

import com.ccsd.backend.dto.ProductDTO;
import com.ccsd.backend.dto.UserDTO;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {
    public final List<UserDTO> INITIAL_USERS = Arrays.asList(
            new UserDTO(
                    "Admin12",
                    "admin@example.com",
                    "123456",
                    true
            ),
            new UserDTO(
                    "User13",
                    "user@example.com",
                    "123456",
                    false
            )
    );

    public final List<ProductDTO> INITIAL_PRODUCTS = Arrays.asList(
            new ProductDTO(
                    "Nike Slim shirt",
                    "nike-slim-shirt",
                    "Shirts",
                    "/images/p1.jpg",
                    120.0,
                    10,
                    "Nike",
                    4.5,
                    10,
                    "high quality shirt"
            ),
            new ProductDTO(
                    "Adidas Fit Shirt",
                    "adidas-fit-shirt",
                    "Shirts",
                    "/images/p2.jpg",
                    250.0,
                    20,
                    "Adidas",
                    4.0,
                    10,
                    "high quality product"
            ),
            new ProductDTO(
                    "Nike Slim Pant",
                    "nike-slim-pant",
                    "Pants",
                    "/images/p3.jpg",
                    25.0,
                    15,
                    "Nike",
                    4.5,
                    14,
                    "high quality product"
            ),
            new ProductDTO(
                    "Adidas Fit Pant",
                    "adidas-fit-pant",
                    "Pants",
                    "/images/p4.jpg",
                    65.0,
                    5,
                    "Puma",
                    4.5,
                    10,
                    "high quality product"
            )
    );
}

