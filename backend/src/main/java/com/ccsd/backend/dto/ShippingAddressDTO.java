package com.ccsd.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressDTO {
    private String fullName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}

