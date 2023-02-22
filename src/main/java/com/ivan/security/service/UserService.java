package com.ivan.security.service;

import com.ivan.security.model.Permission;
import com.ivan.security.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
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
}
