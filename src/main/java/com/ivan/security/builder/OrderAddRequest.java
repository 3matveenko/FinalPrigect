package com.ivan.security.builder;


import com.ivan.security.model.ItemMenu;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddRequest {

    private Long id;
    private String resp;
    private String username;
    private int sum;
    private java.sql.Date date;
    private List<ItemMenu> itemsMenu;
    private List<ListItem> listItems;
    private List<Integer> listsum;
    private List<Date> dates;



}
