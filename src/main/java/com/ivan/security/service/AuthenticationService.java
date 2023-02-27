package com.ivan.security.service;

import com.ivan.security.builder.AuthenticationRequest;
import com.ivan.security.builder.AuthenticationResponse;
import com.ivan.security.builder.RegisterRequest;
import com.ivan.security.model.Permission;
import com.ivan.security.model.User;
import com.ivan.security.repository.PermissionRepository;
import com.ivan.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final JwtService jwtService;


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var search = User.builder()
                .email(request.getEmail())
                        .build();
        User email = repository.findByEmail(search.getEmail());
        if(email==null) {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            user.setPermissions(setCashier());
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var username = user.getUsername();
            var role = userService.getRole(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken).username(username).role(role)
                    .build();
        }
        return AuthenticationResponse.builder().role(100).build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        var username = user.getUsername();
        var role = userService.getRole(user);
        return AuthenticationResponse.builder()
                .username(username)
                .role(role)
                .token(jwtToken)
                .build();
    }

    public List<Permission> setCashier(){
        Permission cashier = permissionRepository.findByName("Cashier");
        List<Permission> newlist = new ArrayList<>();
        newlist.add(cashier);
        return newlist;
    }
}
