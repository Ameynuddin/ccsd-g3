package com.ccsdg3.ecom.controller;

import com.ccsdg3.ecom.dto.SignInRequest;
import com.ccsdg3.ecom.dto.SignUpRequest;
import com.ccsdg3.ecom.dto.AuthResponse;
import com.ccsdg3.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(userService.signIn(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }
}
