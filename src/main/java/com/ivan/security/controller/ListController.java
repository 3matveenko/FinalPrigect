package com.ivan.security.controller;

import com.ivan.security.builder.AddList;
import com.ivan.security.builder.GetDate;
import com.ivan.security.builder.OrderAddRequest;
import com.ivan.security.builder.ProductList;
import com.ivan.security.model.ListProduct;
import com.ivan.security.model.Product;
import com.ivan.security.service.ListService;
import com.ivan.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addlist")
    public ResponseEntity<AddList> addList(
            @RequestBody  AddList addList){
        listService.addList(addList);
        return ResponseEntity.ok(addList);
    }

    @PostMapping("/all_lists")
    public ResponseEntity<List<AddList>> allLists(
            @RequestBody OrderAddRequest request){
        return ResponseEntity.ok(listService.getAllLists(request));
    }
    @PostMapping("/details")
    public ResponseEntity<AddList> details(
            @RequestBody OrderAddRequest request){
        return ResponseEntity.ok(listService.details(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestBody AddList request){
        listService.delete(request);
        return ResponseEntity.ok("OK");
    }

}
