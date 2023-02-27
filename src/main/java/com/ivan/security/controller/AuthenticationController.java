package com.ivan.security.controller;


import com.ivan.security.builder.AuthenticationRequest;
import com.ivan.security.builder.AuthenticationResponse;
import com.ivan.security.builder.ListUsers;
import com.ivan.security.builder.RegisterRequest;
import com.ivan.security.service.AuthenticationService;
import com.ivan.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/old")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthenticationResponse>> all_users(
    ) {
        return ResponseEntity.ok(userService.all_users());
    }
    @PutMapping("/change_role")
    public ResponseEntity<AuthenticationResponse> change_role(
            @RequestBody AuthenticationResponse response
    ) {
        return ResponseEntity.ok(userService.change_role(response));
    }

}
