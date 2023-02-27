package com.ivan.security.builder;

import com.ivan.security.model.Product;
import com.ivan.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddList {

    private  Long id;
    private String nameUser;

    private List<Product> products;
    private int sum;
    private Date date;
}
