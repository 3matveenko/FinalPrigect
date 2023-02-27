package com.ivan.security.service;

import com.ivan.security.builder.AddList;
import com.ivan.security.builder.GetDate;
import com.ivan.security.builder.OrderAddRequest;
import com.ivan.security.model.ListProduct;
import com.ivan.security.model.Money;
import com.ivan.security.model.Product;
import com.ivan.security.model.User;
import com.ivan.security.repository.ListRepository;
import com.ivan.security.repository.MoneyRepository;
import com.ivan.security.repository.ProductRepository;
import com.ivan.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListRepository listRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final MoneyRepository moneyRepository;

public List<Product> getReminder(){
    return productRepository.findAll();
}

public void addList(AddList addList){
    var list = ListProduct.builder()
            .nameUser(getUser(addList.getNameUser()))
            .sum(changeSum(addList.getSum()))
            .date(addList.getDate())
            .products(setId(addList.getProducts()))
            .build();
    listRepository.save(list);
}

public List<Product> setId(List<Product> products){
    for(Product p : products){
        if(p.getItem().equals("молоко")){
            p.setId(2L);
            p.setUnit("мл");
            Product product = productRepository.findByItem("молоко");
            product.setAmount(product.getAmount() + p.getAmount());
            productRepository.save(product);
        }
        if(p.getItem().equals("зерно")){
            p.setId(1L);
            p.setUnit("гр");
            Product product = productRepository.findByItem("зерно");
            product.setAmount(product.getAmount() + p.getAmount());
            productRepository.save(product);
        }
        if(p.getItem().equals("сливки")){
            p.setId(3L);
            p.setUnit("мл");
            Product product = productRepository.findByItem("сливки");
            product.setAmount(product.getAmount() + p.getAmount());
            productRepository.save(product);
        }
        if(p.getItem().equals("сироп")){
            p.setId(4L);
            p.setUnit("мл");
            Product product = productRepository.findByItem("сироп");
            product.setAmount(product.getAmount() + p.getAmount());
            productRepository.save(product);
        }
    }
    return products;
}
 public Integer changeSum(Integer sum){
   Money money =  moneyRepository.findById(1L).orElseThrow();
   money.setAmount(money.getAmount()-sum);
   moneyRepository.save(money);
   return sum;
 }

 public List<AddList> getAllLists(OrderAddRequest request){
 List<Date> dates = request.getDates();
 Date date1 = dates.get(0);
 Date date2 = dates.get(1);
 List<ListProduct> lists = listRepository.findAllByDateBetween(date1, date2);
    List<AddList> addLists = new ArrayList<>();

    for(ListProduct l : lists){
        AddList a = new AddList();
        a.setId(l.getId());
        a.setNameUser(l.getNameUser().getUsername());
        a.setSum(l.getSum());
        a.setDate(l.getDate());
        a.setProducts(l.getProducts());
        addLists.add(a);

    }
    return addLists;
 }

 public AddList details(OrderAddRequest request){
   ListProduct listProduct =  listRepository.findById(request.getId()).orElseThrow();
   AddList addList = new AddList();
   addList.setProducts(listProduct.getProducts());
   return addList;
 }
public User getUser(String name){
    return userRepository.findByUsername(name);
}

public void delete(AddList addList){
    ListProduct listProduct = listRepository.findById(addList.getId()).orElseThrow();
    listRepository.delete(listProduct);
}
}
