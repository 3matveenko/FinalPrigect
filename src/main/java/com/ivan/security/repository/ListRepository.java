package com.ivan.security.repository;

import com.ivan.security.model.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ListRepository extends JpaRepository<ListProduct, Long> {

}
