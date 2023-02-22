package com.ivan.security.repository;

import com.ivan.security.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface OrderRepositiry extends JpaRepository<Order, Long> {
    List<Order> findOrderByDate(Date date);
    List<Order> findAllByDateBetween(Date date1,Date date2);

}
