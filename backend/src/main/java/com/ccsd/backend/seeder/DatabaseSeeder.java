package com.ccsd.backend.seeder;

import com.ccsd.backend.dto.ProductDTO;
import com.ccsd.backend.dto.UserDTO;
import com.ccsd.backend.model.Product;
import com.ccsd.backend.model.User;
import com.ccsd.backend.repository.ProductRepository;
import com.ccsd.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatabaseSeeder {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataInitializer dataInitializer;

    @EventListener(ApplicationReadyEvent.class)
    public void seedDatabase() {
        if (userRepository.count() == 0 && productRepository.count() == 0) {
            log.info("Seeding database...");
            seedUsers();
            seedProducts();
            log.info("Database seeding completed");
        }
    }

    private void seedUsers() {
        dataInitializer.INITIAL_USERS.stream()
                .map(this::convertToUser)
                .forEach(userRepository::save);
    }

    private void seedProducts() {
        dataInitializer.INITIAL_PRODUCTS.stream()
                .map(this::convertToProduct)
                .forEach(productRepository::save);
    }

    private User convertToUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAdmin(dto.isAdmin());
        return user;
    }

    private Product convertToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setSlug(dto.getSlug());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setCountInStock(dto.getCountInStock());
        product.setBrand(dto.getBrand());
        product.setRating(dto.getRating());
        product.setNumReviews(dto.getNumReviews());
        product.setDescription(dto.getDescription());
        return product;
    }
}

