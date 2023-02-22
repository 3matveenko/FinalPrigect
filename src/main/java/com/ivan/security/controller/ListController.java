package com.ivan.security.controller;

import com.ivan.security.builder.AddList;
import com.ivan.security.builder.ProductList;
import com.ivan.security.model.Product;
import com.ivan.security.service.ListService;
import com.ivan.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ListController {

private final ListService listService;

    @GetMapping("/remainder")
    public ResponseEntity<List<Product>> gerRem(){
    return ResponseEntity.ok(listService.getReminder());
}
}
