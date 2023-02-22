package com.ivan.security.service;

import com.ivan.security.builder.AddList;
import com.ivan.security.model.Product;
import com.ivan.security.repository.ListRepository;
import com.ivan.security.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListRepository listRepository;
    private final ProductRepository productRepository;


public List<Product> getReminder(){
    return productRepository.findAll();
}
}
