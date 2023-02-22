package com.ivan.security.controller;

import com.ivan.security.builder.OrderAddRequest;

import com.ivan.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/cash_year")
    public ResponseEntity<OrderAddRequest> cashYear(
            @RequestBody OrderAddRequest orderAddRequest
    ){
        return ResponseEntity.ok(orderService.getCashYear(orderAddRequest));
    }
    @PostMapping("/add")
    public ResponseEntity<OrderAddRequest> addOrder(
            @RequestBody OrderAddRequest request
    ){

        return ResponseEntity.ok(orderService.addOrder(request));
    }

    @PostMapping("/all")
    public ResponseEntity<List<OrderAddRequest>> getOrder(
            @RequestBody OrderAddRequest request
    ){
        return ResponseEntity.ok(orderService.getOrder(request));
    }
    @PostMapping("/get_by_id")
    public ResponseEntity<OrderAddRequest> orderById(
            @RequestBody OrderAddRequest request
    ) {
        return ResponseEntity.ok(orderService.orderById(request));
    }


}
