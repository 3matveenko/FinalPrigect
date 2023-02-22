package com.ivan.security.service;


import com.ivan.security.builder.ListItem;
import com.ivan.security.builder.OrderAddRequest;
import com.ivan.security.model.ItemMenu;
import com.ivan.security.model.Money;
import com.ivan.security.model.Order;
import com.ivan.security.model.Product;
import com.ivan.security.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepositiry orderRepositiry;
    private final UserRepository userRepository;
    private final ItemMenuRepository itemMenuRepository;
    private final ProductRepository productRepository;

    private final MoneyRepository moneyRepository;

    public OrderAddRequest addOrder(OrderAddRequest request) {
        var order = Order.builder()
                .username(userRepository.findByUsername(request.getUsername()))
                .sum(changeMoney(request.getSum()))
                .date((java.sql.Date) request.getDate())
                .itemsMenu(setItemMenu(request.getItemsMenu()))
                .build();
        orderRepositiry.save(order);
        OrderAddRequest resp = new OrderAddRequest();
        resp.setResp("OK");
        return resp;
    }
    public List<OrderAddRequest> getOrder(OrderAddRequest request) {
        List<Order> ords = orderRepositiry.findOrderByDate(request.getDate());
        List<OrderAddRequest> orders = new ArrayList<>();
            for (Order ords1 : ords) {
                OrderAddRequest order = new OrderAddRequest();
                order.setId(ords1.getId());
                order.setUsername(ords1.getUsername().getUsername());
                order.setDate(ords1.getDate());
                order.setSum(ords1.getSum());
                orders.add(order);
            }
            return orders;
        }

    public OrderAddRequest orderById(OrderAddRequest request){
        Long ID = request.getId();
        Order neworder = orderRepositiry.findById(ID).orElseThrow();
        return OrderAddRequest.builder()
                .id(neworder.getId())
                .sum(neworder.getSum())
                .itemsMenu(neworder.getItemsMenu())
                .build();
    }

    public OrderAddRequest getCashYear(OrderAddRequest orderAddRequest){

        try {
            List<Date> newDat = orderAddRequest.getDates();
            String d0101 = "01/01/2023";
            Date firstJ = new SimpleDateFormat("dd/MM/yyyy").parse(d0101);
            String d3101 = "31/01/2023";
            Date lastJ = new SimpleDateFormat("dd/MM/yyyy").parse(d3101);
            Date first = newDat.get(0);



            List<Order> January = orderRepositiry.findAllByDateBetween(first, lastJ);
            int sumJanuary = 0;
            for(Order sum: January) {
                sumJanuary = sum.getSum() + sumJanuary;
            }
        String d0102="01/02/2023";
        Date firstF=new SimpleDateFormat("dd/MM/yyyy").parse(d0102);
        String d2802="28/02/2023";
        Date lastF=new SimpleDateFormat("dd/MM/yyyy").parse(d2802);
        Date second = newDat.get(1);

        List<Order> February = orderRepositiry.findAllByDateBetween(firstF, second);
        int sumFebruary = 0;
        for(Order sum: February){
            sumFebruary =sum.getSum()+ sumFebruary;
        }
  List<Integer> summ = new ArrayList<>();
            summ.add(sumJanuary);
        summ.add(sumFebruary);


        List<Order> items = orderRepositiry.findAllByDateBetween(first,second);
            ;
            var order = OrderAddRequest.builder()
                    .listItems(getItems(items))
                    .listsum(summ).build();
            return order;
        } catch (Exception e){
            System.out.println(e);
        } 

return null;
    }
    

    public List<ItemMenu> setItemMenu(List<ItemMenu> list) {
        for (ItemMenu l : list) {
            if (l.getItemMenuName().equals("эспрессо")) {
               Amount_coffe(8);
               l.setId(itemMenuRepository.findByItemMenuName("эспрессо").getId());

            }
            if (l.getItemMenuName().equals("американо")) {
                Amount_coffe(8);
                l.setId(itemMenuRepository.findByItemMenuName("американо").getId());
            }
            if (l.getItemMenuName().equals("капучино")) {
                Amount_coffe(8);
                Amount_milk(220);
                l.setId(itemMenuRepository.findByItemMenuName("капучино").getId());
            }

                if (l.getItemMenuName().equals("латте")) {
                    Amount_coffe(8);
                    Amount_milk(230);
                    l.setId(itemMenuRepository.findByItemMenuName("латте").getId());
                }
                if (l.getItemMenuName().equals("ванильный_раф")) {
                    Amount_coffe(14);
                    Amount_cream(230);
                    Amount_syrop(30);
                    l.setId(itemMenuRepository.findByItemMenuName("ванильный_раф").getId());
                }
                if (l.getItemMenuName().equals("фирменный_раф")) {
                    Amount_coffe(14);
                    Amount_cream(130);
                    Amount_milk(100);
                    Amount_syrop(30);
                    l.setId(itemMenuRepository.findByItemMenuName("фирменный_раф").getId());
                }

        }
        return  list;
    }

        public void Amount_coffe ( int gr){
            Product coffee = productRepository.findByItem("зерно");
            int new_coffee = coffee.getAmount() - gr;
            coffee.setAmount(new_coffee);
            productRepository.save(coffee);

        }
        public void Amount_milk ( int gramm){
            Product milk = productRepository.findByItem("молоко");
            int new_milk = milk.getAmount() - gramm;
            milk.setAmount(new_milk);
            productRepository.save(milk);

        }

        public void Amount_cream ( int gramm){
            Product cream = productRepository.findByItem("сливки");
            int new_cream = cream.getAmount() - gramm;
            cream.setAmount(new_cream);
            productRepository.save(cream);

        }
        public void Amount_syrop ( int gramm){
            Product syrop = productRepository.findByItem("сироп");
            int new_syrop = syrop.getAmount() - gramm;
            syrop.setAmount(new_syrop);
            productRepository.save(syrop);

        }
        public  Integer changeMoney(int sum){
            Money money = moneyRepository.findById(1L).orElseThrow();
            int newAmount =  money.getAmount() + sum;
            money.setAmount(newAmount);
            moneyRepository.save(money);
            return sum;
        }

        public List<ListItem> getItems(List<Order> list){
        int espresso = 0;
        int americano = 0;
        int capu = 0;
        int latte = 0;
        int r_f = 0;
        int r_v = 0;
        for (Order o : list){
            List<ItemMenu> newlist = o.getItemsMenu();
            for(ItemMenu i: newlist){
                if (i.getItemMenuName().equals("эспрессо")) {
                    espresso++;
                }
                if (i.getItemMenuName().equals("американо")) {
                    americano++;
                }
                if (i.getItemMenuName().equals("капучино")) {
                    capu++;
                }

                if (i.getItemMenuName().equals("латте")) {
                    latte++;
                }
                if (i.getItemMenuName().equals("ванильный_раф")) {
                   r_v++;
                }
                if (i.getItemMenuName().equals("фирменный_раф")) {
                    r_f++;
                }
            }

        }

            ListItem listItem = new ListItem();
            listItem.setName("эспрессо");
            listItem.setAmount(espresso);
            ListItem listItem1 = new ListItem();
            listItem1.setName("американо");
            listItem1.setAmount(americano);
            ListItem listItem2 = new ListItem();
            listItem2.setName("латте");
            listItem2.setAmount(latte);
            ListItem listItem3 = new ListItem();
            listItem3.setName("ванильный_раф");
            listItem3.setAmount(r_v);
            ListItem listItem4 = new ListItem();
            listItem4.setName("капучино");
            listItem4.setAmount(capu);
            ListItem listItem5 = new ListItem();
            listItem5.setName("фирменный_раф");
            listItem5.setAmount(r_f);
            List<ListItem>listItems = new ArrayList<>();
            listItems.add(listItem);
            listItems.add(listItem1);
            listItems.add(listItem2);
            listItems.add(listItem3);
            listItems.add(listItem4);
            listItems.add(listItem5);
            return listItems;
        }

}
