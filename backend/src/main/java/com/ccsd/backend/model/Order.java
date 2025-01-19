package com.ccsd.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private List<OrderItem> orderItems;
    private ShippingAddress shippingAddress;
    private String paymentMethod;
    private PaymentResult paymentResult;
    private Double itemsPrice;
    private Double shippingPrice;
    private Double taxPrice;
    private Double totalPrice;

    @DBRef
    private User user;

    private boolean isPaid = false;
    private Date paidAt;
    private boolean isDelivered = false;
    private Date deliveredAt;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
