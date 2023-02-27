package com.ivan.security.service;

import com.ivan.security.builder.AuthenticationResponse;
import com.ivan.security.builder.ListUsers;
import com.ivan.security.model.Permission;
import com.ivan.security.model.User;
import com.ivan.security.repository.PermissionRepository;
import com.ivan.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
   UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public int getRole(User user) {
        int count = 0;
        List<Permission> role = user.getPermissions();
        for (Permission a : role) {
            if (a.getName().equals("Cashier")) {
                count++;
            }
            if (a.getName().equals("Storekeeper")) {
                count++;
            }
            if (a.getName().equals("Admin")) {
                count++;
            }
        }
        if (count == 1) {
            return 1;
        } else if (count == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    public List<AuthenticationResponse> all_users(){
    List<User> users = userRepository.findAll();
    List<AuthenticationResponse> respUsers = new ArrayList<>();
    for(User u : users){
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setId(u.getId());
        authenticationResponse.setUsername(u.getUsername());
        authenticationResponse.setRole(getRole(u));
        respUsers.add(authenticationResponse);
    }
    return respUsers;
    }

    public AuthenticationResponse change_role(AuthenticationResponse response){
        User user = userRepository.findById(response.getId()).orElseThrow();
        Permission cashier = permissionRepository.findById(1L).orElseThrow();
        Permission storekeeper = permissionRepository.findById(2L).orElseThrow();
        Permission admin = permissionRepository.findById(3L).orElseThrow();
        List<Permission> one = new ArrayList<>();
        one.add(cashier);
        List<Permission> two = new ArrayList<>();
        two.add(cashier);
        two.add(storekeeper);
        List<Permission> three = new ArrayList<>();
        three.add(cashier);
        three.add(storekeeper);
        three.add(admin);
        if(response.getNewRole()==1){
            user.setPermissions(one);
            userRepository.save(user);
        }
        if(response.getNewRole()==2){
            user.setPermissions(two);
            userRepository.save(user);
        }
        if(response.getNewRole()==3){
            user.setPermissions(three);
            userRepository.save(user);
        }
        return response;
    }
}
