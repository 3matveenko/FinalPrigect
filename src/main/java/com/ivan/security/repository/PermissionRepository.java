package com.ivan.security.repository;


import com.ivan.security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);

}
