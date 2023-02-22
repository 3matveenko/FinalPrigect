package com.ivan.security.controller;


import com.ivan.security.builder.AuthenticationRequest;
import com.ivan.security.builder.AuthenticationResponse;
import com.ivan.security.builder.RegisterRequest;
import com.ivan.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/new")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/old")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
