package com.ivan.security.repository;
import com.ivan.security.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {

}
