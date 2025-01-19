package com.ccsd.backend.dto;

import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderItemRequest> orderItems;
    private ShippingAddressDTO shippingAddress;
    private String paymentMethod;
    private Double itemsPrice;
    private Double shippingPrice;
    private Double taxPrice;
    private Double totalPrice;
}

