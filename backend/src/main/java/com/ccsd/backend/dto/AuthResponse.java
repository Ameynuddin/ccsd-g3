package com.ccsd.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String id;
    private String name;
    private String email;
    private Boolean isAdmin;
    private String token;
}
