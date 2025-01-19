package com.ccsd.backend.repository;

import com.ccsd.backend.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
    List<Order> findByUserIdOrderByCreatedAtDesc(String userId);
}
